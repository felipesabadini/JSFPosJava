package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import entidade.Estado;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "listaEstado",
            pattern = "/estado/listar",
            viewId = "/faces/estado/estadolista.xhtml")
    ,
    @URLMapping(id = "novoEstado",
            pattern = "/estado/novo",
            viewId = "/faces/estado/estadoedita.xhtml")
    ,
    @URLMapping(id = "editaEstado",
            pattern = "/estado/editar/#{estadoControle.id}",
            viewId = "/faces/estado/estadoedita.xhtml")
})
public class EstadoControle implements Serializable {
    
    @EJB
    private EstadoFacade estadoFacade;
    
    private String id;

    private Estado estado;

    public List<Estado> listaTodos() {
        return estadoFacade.listaTodos();
    }

    public void salvar() {
        estadoFacade.salvar(estado);
    }

    @URLAction(mappingId = "novoEstado", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        estado = new Estado();
    }

    public void excluir(Estado e) {
        estadoFacade.remover(e);
    }

    public void alterar(Estado e) {
        this.estado = e;
    }
    
    @URLAction(mappingId = "editaEstado", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void alterarPelaURL(){
        this.estado = estadoFacade.buscar(Long.parseLong(id));
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
}
