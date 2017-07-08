package controle;

import entidade.Pessoa;
import entidade.PessoaFisica;
import entidade.PessoaJuridica;
import facade.PessoaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PessoaControle {
    @EJB
    private PessoaFacade pessoaFacade;
    
    @ManagedProperty (value = "#{pessoaFisicaControle}")
    private PessoaFisicaControle pessoaFisicaControle;
    
    @ManagedProperty (value = "#{pessoaJuridicaControle}")
    private PessoaJuridicaControle pessoaJuridicaControle;
    
    public String editar(Pessoa p) {
        if (p instanceof PessoaFisica) {
            pessoaFisicaControle.setPessoaFisica((PessoaFisica) p);
            return "pessoafisicaedita";
        } else {
            pessoaJuridicaControle.setPessoaJuridica((PessoaJuridica) p);
            return "pessoajuridicaedita";
        }
    }
    
    public void excluir(Pessoa p) {
        if (p instanceof PessoaFisica) {
            pessoaFisicaControle.excluir((PessoaFisica) p);
        } else {
            pessoaJuridicaControle.excluir((PessoaJuridica) p);
        }
    }
    
    public List<Pessoa> listaTodos(){
        return pessoaFacade.listaTodos();
    }

    public PessoaFisicaControle getPessoaFisicaControle() {
        return pessoaFisicaControle;
    }

    public void setPessoaFisicaControle(PessoaFisicaControle pessoaFisicaControle) {
        this.pessoaFisicaControle = pessoaFisicaControle;
    }

    public PessoaJuridicaControle getPessoaJuridicaControle() {
        return pessoaJuridicaControle;
    }

    public void setPessoaJuridicaControle(PessoaJuridicaControle pessoaJuridicaControle) {
        this.pessoaJuridicaControle = pessoaJuridicaControle;
    }
    
}
