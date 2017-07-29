package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import entidade.PessoaFisica;
import facade.PessoaFisicaFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "novaPessoaFisica",
            pattern = "/pessoa/fisica/novo",
            viewId = "/faces/pessoa/pessoafisicaedita.xhtml")
    ,
    @URLMapping(id = "editaPessoaFisica",
            pattern = "/pessoa/fisica/editar/#{pessoaFisicaControle.id}",
            viewId = "/faces/pessoa/pessoafisicaedita.xhtml")
})
public class PessoaFisicaControle implements Serializable {

    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    
    private String id;

    private PessoaFisica pessoaFisica;

    public void salvar(){
        pessoaFisicaFacade.salvar(pessoaFisica);
    }
    
    public void excluir(PessoaFisica pf){
        pessoaFisicaFacade.remover(pf);
    }
    
    @URLAction(mappingId = "novaPessoaFisica", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo(){
        pessoaFisica = new PessoaFisica();
    }
    
    @URLAction(mappingId = "editaPessoaFisica", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void alterarPelaURL(){
        this.pessoaFisica = pessoaFisicaFacade.buscar(Long.parseLong(id));
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

}
