package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.ItensVenda;
import entidade.Pessoa;
import entidade.Produto;
import entidade.Venda;
import facade.PessoaFacade;
import facade.ProdutoFacade;
import facade.VendaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class VendaControle {

    @EJB
    private VendaFacade vendaFacade;
    
    @EJB
    private PessoaFacade pessoaFacade;
    
    @EJB
    private ProdutoFacade produtoFacade;
    
    private Venda venda;
    
    private ItensVenda itensVenda;
    
    private ConverterGenerico converterPessoa;
    
    private ConverterGenerico converterProduto;
    
    private MoneyConverter moneyConverter;
    
    public void salvar() {
        vendaFacade.salvar(venda);
    }

    public void novo() {
        venda = new Venda();
        itensVenda = new ItensVenda();
    }

    public void excluir(Venda v) {
        vendaFacade.remover(v);
    }

    public void alterar(Venda v) {
        this.venda = v;
        itensVenda = new ItensVenda();
    }
    
    public List<Venda> listaTodos(){
        return vendaFacade.listaTodos();
    }
    
    public List<Pessoa> listaPessoas(String parte) {
        return pessoaFacade.listaFiltrando(parte, "nome");
    }
    
    public List<Produto> listaProdutos(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }
    
    public void atualizaPreco() {
        itensVenda.setPreco(itensVenda.getProduto().getPreco());
    }
    
    public void addItem() {
        Double estoque = itensVenda.getProduto().getEstoque();
        for(ItensVenda it: venda.getItensVendas()) {
            if(itensVenda.getProduto().equals(it.getProduto())) {
                estoque = estoque - it.getQuantidade();
            }
        }
        if(estoque >= itensVenda.getQuantidade()) {
            Boolean existeNaLista = false;
            for(ItensVenda it: venda.getItensVendas()) {
                if(it.getProduto().equals(itensVenda.getProduto())) {
                    it.setQuantidade(it.getQuantidade() + itensVenda.getQuantidade());
                    it.setPreco(itensVenda.getPreco());
                    existeNaLista = true;
                }
            }
            if(!existeNaLista) {
                itensVenda.setVenda(venda);
                venda.getItensVendas().add(itensVenda);
            }
            itensVenda = new ItensVenda();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Estoque insuficiente.", "Restam apenas " + estoque + " unidades."));
        }
    }
    
    public void removerItem(ItensVenda it) {
        venda.getItensVendas().remove(it);
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
    }

    public ConverterGenerico getConverterPessoa() {
        if(converterPessoa == null) {
            converterPessoa = new ConverterGenerico(pessoaFacade);
        }
        return converterPessoa;
    }

    public void setConverterPessoa(ConverterGenerico converterPessoa) {
        this.converterPessoa = converterPessoa;
    }

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public ConverterGenerico getConverterProduto() {
        if(converterProduto == null) {
            converterProduto = new ConverterGenerico(produtoFacade);
        }
        return converterProduto;
    }

    public void setConverterProduto(ConverterGenerico converterProduto) {
        this.converterProduto = converterProduto;
    }
    
    
}
