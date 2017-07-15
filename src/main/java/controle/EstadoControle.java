package controle;

import entidade.Estado;
import facade.EstadoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EstadoControle {

    private Estado estado;
    @EJB
    private EstadoFacade estadoFacade;
    
    public List<Estado> listaTodos(){
        return estadoFacade.listaTodos();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void salvar() {
        estadoFacade.salvar(estado);
    }

    public void novo() {
        estado = new Estado();
    }

    public void excluir(Estado e) {
        estadoFacade.remover(e);
    }

    public void alterar(Estado e) {
        this.estado = e;
    }

}
