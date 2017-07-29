package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.GrupoProduto;
import entidade.Produto;
import facade.GrupoProdutoFacade;
import facade.ProdutoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "listaProduto",
            pattern = "/produto/listar",
            viewId = "/faces/produto/produtolista.xhtml")
    ,
    @URLMapping(id = "novoProduto",
            pattern = "/produto/novo",
            viewId = "/faces/produto/produtoedita.xhtml")
    ,
    @URLMapping(id = "editaProduto",
            pattern = "/produto/editar/#{produtoControle.id}",
            viewId = "/faces/produto/produtoedita.xhtml")
})
public class ProdutoControle implements Serializable {

    @EJB
    private ProdutoFacade produtoFacade;
    
    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
    
    private String id;
    
    private Produto produto;
    
    private ConverterGenerico converterGrupo;
    
    private MoneyConverter moneyConverter;

    public List<GrupoProduto> listaGrupos(String parte){
        return grupoProdutoFacade.listaFiltrando(parte, "nome");
    }

    public List<Produto> listaTodos() {
        return produtoFacade.listaTodos();
    }

    public void salvar() {
        produtoFacade.salvar(produto);
    }

    @URLAction(mappingId = "novoProduto", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        produto = new Produto();
    }

    public void excluir(Produto e) {
        produtoFacade.remover(e);
    }

    public void alterar(Produto e) {
        this.produto = e;
    }
    
    @URLAction(mappingId = "editaProduto", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void alterarPelaURL(){
        this.produto = produtoFacade.buscar(Long.parseLong(id));
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public ConverterGenerico getConverterGrupo() {
        if(converterGrupo == null){
            converterGrupo = new ConverterGenerico(grupoProdutoFacade);
        }
        return converterGrupo;
    }

    public void setConverterGrupo(ConverterGenerico converterGrupo) {
        this.converterGrupo = converterGrupo;
    }
    
    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

}
