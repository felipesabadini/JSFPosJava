package controle;

import converter.ConverterGenerico;
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
    
    private GrupoProduto grupoProduto;
    
    private Produto produto;
    
    private ConverterGenerico converterGrupoProduto;
    
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

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
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

}
