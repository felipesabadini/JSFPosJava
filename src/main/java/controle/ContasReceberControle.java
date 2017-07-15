package controle;

import entidade.ContasReceber;
import facade.ContasReceberFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ContasReceberControle {

    private ContasReceber contasReceber;
    @EJB
    private ContasReceberFacade contasReceberFacade;
    
    public List<ContasReceber> listaTodos(){
        return contasReceberFacade.listaTodos();
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public void salvar() {
        contasReceberFacade.salvar(contasReceber);
    }

    public void novo() {
        contasReceber = new ContasReceber();
    }

    public void excluir(ContasReceber cr) {
        contasReceberFacade.remover(cr);
    }

    public void alterar(ContasReceber cr) {
        this.contasReceber = cr;
    }

}
