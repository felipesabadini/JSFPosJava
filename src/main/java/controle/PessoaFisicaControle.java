package controle;

import entidade.Pessoa;
import entidade.PessoaFisica;
import facade.PessoaFisicaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PessoaFisicaControle {
    private PessoaFisica pessoaFisica;
    
    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    
    public void salvar() {
        pessoaFisicaFacade.salvar(pessoaFisica);
    }
    
    public void excluir(PessoaFisica p) {
        pessoaFisicaFacade.remover(p);
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
    
    public void novo(){
        pessoaFisica = new PessoaFisica();
    }
}
