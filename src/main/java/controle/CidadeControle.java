package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import converter.ConverterGenerico;
import entidade.Cidade;
import facade.CidadeFacade;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "listaCidade",
            pattern = "/cidade/listar",
            viewId = "/faces/cidade/cidadelista.xhtml")
    ,
    @URLMapping(id = "novaCidade",
            pattern = "/cidade/novo",
            viewId = "/faces/cidade/cidadeedita.xhtml")
    ,
    @URLMapping(id = "editaCidade",
            pattern = "/cidade/editar/#{cidadeControle.id}",
            viewId = "/faces/cidade/cidadeedita.xhtml")
})
public class CidadeControle implements Serializable {
    
    @EJB
    private EstadoFacade estadoFacade;
    
    @EJB
    private CidadeFacade cidadeFacade;
    
    private String id;

    private Cidade cidade = new Cidade();
    
    private ConverterGenerico estadoConverter;

    public void salvar() {
        cidadeFacade.salvar(cidade);
    }

    @URLAction(mappingId = "novaCidade", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        cidade = new Cidade();
    }

    public void excluir(Cidade cid) {
        cidadeFacade.remover(cid);
    }

    public void alterar(Cidade cid) {
        this.cidade = cid;
    }
    
    @URLAction(mappingId = "editaCidade", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void alterarPelaURL(){
        this.cidade = cidadeFacade.buscar(Long.parseLong(id));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public ConverterGenerico getEstadoConverter() {
        if(estadoConverter == null){
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public void setEstadoConverter(ConverterGenerico estadoConverter) {
        this.estadoConverter = estadoConverter;
    }

    public List<Cidade> getListaTodos() {
        return cidadeFacade.listaTodos();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
