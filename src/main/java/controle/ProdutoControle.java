package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.GrupoProduto;
import entidade.Produto;
import facade.GrupoProdutoFacade;
import facade.ProdutoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProdutoControle {
  
    @EJB
    private ProdutoFacade produtoFacade;
    
    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
       
    private Produto produto;
    
    private ConverterGenerico converterGrupoProduto;
    
    private MoneyConverter moneyConverter;
    
    public List<Produto> listaTodos(){
        return produtoFacade.listaTodos();
    }
    
    public List<GrupoProduto> listaGrupoProduto(String parte) {
        return grupoProdutoFacade.listaFiltrando(parte, "nome");
    }

    public void salvar() {
        produtoFacade.salvar(produto);
    }

    public void novo() {
        produto = new Produto();
    }

    public void excluir(Produto p) {
        produtoFacade.remover(p);
    }

    public void alterar(Produto p) {
        this.produto = p;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public ConverterGenerico getConverterGrupoProduto() {
        if (converterGrupoProduto == null) {
            converterGrupoProduto = new ConverterGenerico(grupoProdutoFacade);
        }
        return converterGrupoProduto;
    }

    public void setConverterCidade(ConverterGenerico converterGrupoProduto) {
        this.converterGrupoProduto = converterGrupoProduto;
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
    
    
    

}
