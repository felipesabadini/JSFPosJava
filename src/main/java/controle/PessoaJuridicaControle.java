package controle;

import entidade.PessoaJuridica;
import facade.PessoaJuridicaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PessoaJuridicaControle {
    private PessoaJuridica pessoaJuridica;
    
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    
    public void salvar() {
        pessoaJuridicaFacade.salvar(pessoaJuridica);
    }
    
    public void excluir(PessoaJuridica p) {
        pessoaJuridicaFacade.remover(p);
    }
    
    public void novo(){
        pessoaJuridica = new PessoaJuridica();
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
    
}
