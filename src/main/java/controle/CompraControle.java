package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.Compra;
import entidade.ContasPagar;
import entidade.ItensCompra;
import entidade.PessoaJuridica;
import entidade.Produto;
import facade.ProdutoFacade;
import facade.CompraFacade;
import facade.PessoaJuridicaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "listaCompra",
            pattern = "/compra/listar",
            viewId = "/faces/compra/compralista.xhtml")
    ,
    @URLMapping(id = "novaCompra",
            pattern = "/compra/novo",
            viewId = "/faces/compra/compraedita.xhtml")
    ,
    @URLMapping(id = "editaCompra",
            pattern = "/compra/editar/#{compraControle.id}",
            viewId = "/faces/compra/compraedita.xhtml")
})
public class CompraControle implements Serializable {

    @EJB
    private CompraFacade compraFacade;
    
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    
    @EJB
    private ProdutoFacade produtoFacade;
    
    private String id;
    
    private Compra compra;
    
    private ItensCompra itensCompra;
    
    private ConverterGenerico converterPessoa;
    
    private MoneyConverter moneyConverter;
    
    private ConverterGenerico converterProduto;
    
    private Date dataPrimeiraParcela;
    
    public void gerarParcelas(){
        compra.setListaContasPagar(new ArrayList<ContasPagar>());
        Date dataParcela = dataPrimeiraParcela;
        for (int i = 1; i <= compra.getNumeroParcelas(); i++) {
            ContasPagar cr = new ContasPagar();
            cr.setDataLancamento(compra.getDataCompra());
            cr.setCompra(compra);
            cr.setPessoa(compra.getPessoaJuridica());
            cr.setValor(compra.getValorTotal() / compra.getNumeroParcelas());
            cr.setParcela(i);
            cr.setVencimento(dataParcela);
            compra.getListaContasPagar().add(cr);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataParcela);
            cal.add(Calendar.MONTH, 1);
            dataParcela = cal.getTime();            
        }
    }

    public void addItem() {
        Boolean existeNaliSta = false;
        for (ItensCompra iv : compra.getItensCompras()) {
            if (iv.getProduto().equals(itensCompra.getProduto())) {
                iv.setQuantidade(iv.getQuantidade() + itensCompra.getQuantidade());
                iv.setPreco(itensCompra.getPreco());
                existeNaliSta = true;
            }
        }
        if (!existeNaliSta) {
            itensCompra.setCompra(compra);
            compra.getItensCompras().add(itensCompra);
        }
        itensCompra = new ItensCompra();
    }

    public void removerItem(ItensCompra it) {
        it.setCompra(null);
        compra.getItensCompras().remove(it);
    }

    public void atualizaPreco() {
        itensCompra.setPreco(itensCompra.getProduto().getPreco());
    }

    public List<Produto> listaProdutos(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }
    
    public List<PessoaJuridica> listaPessoas(String parte) {
        return pessoaJuridicaFacade.listaFiltrando(parte, "nome");
    }
    
    public List<Compra> listaTodos() {
        return compraFacade.listaTodos();
    }
    
    public void salvar() {
        compraFacade.salvar(compra);
    }

    @URLAction(mappingId = "novaCompra", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        compra = new Compra();
        itensCompra = new ItensCompra();
    }

    public void excluir(Compra c) {
        compraFacade.remover(c);
    }

    public void alterar(Compra c) {
        this.compra = c;
        itensCompra = new ItensCompra();
    }
    
    @URLAction(mappingId = "editaCompra", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void alterarPelaURL(){
        this.compra = compraFacade.buscar(Long.parseLong(id));
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConverterGenerico getConverterProduto() {
        if (converterProduto == null) {
            converterProduto = new ConverterGenerico(produtoFacade);
        }
        return converterProduto;
    }

    public void setConverterProduto(ConverterGenerico converterProduto) {
        this.converterProduto = converterProduto;
    }

    public ItensCompra getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItensCompra itensCompra) {
        this.itensCompra = itensCompra;
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public ConverterGenerico getConverterPessoa() {
        if (converterPessoa == null) {
            converterPessoa = new ConverterGenerico(pessoaJuridicaFacade);
        }
        return converterPessoa;
    }

    public void setConverterPessoa(ConverterGenerico converterPessoa) {
        this.converterPessoa = converterPessoa;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Date getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(Date dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }

}
