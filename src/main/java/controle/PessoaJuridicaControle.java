package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import entidade.PessoaJuridica;
import facade.PessoaJuridicaFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "novaPessoaJuridica",
            pattern = "/pessoa/juridica/novo",
            viewId = "/faces/pessoa/pessoajuridicaedita.xhtml")
    ,
    @URLMapping(id = "editaPessoaJuridica",
            pattern = "/pessoa/juridica/editar/#{pessoaFisicaControle.id}",
            viewId = "/faces/pessoa/pessoajuridicaedita.xhtml")
})
public class PessoaJuridicaControle implements Serializable {

    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    
    private String id;
        
    private PessoaJuridica pessoaJuridica;

    public void salvar(){
        pessoaJuridicaFacade.salvar(pessoaJuridica);
    }
    
    public void excluir(PessoaJuridica pj){
        pessoaJuridicaFacade.remover(pj);
    }
    
    @URLAction(mappingId = "novaPessoaJuridica", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo(){
        pessoaJuridica = new PessoaJuridica();
    }
    
    @URLAction(mappingId = "editaPessoaJuridica", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void alterarPelaURL(){
        this.pessoaJuridica = pessoaJuridicaFacade.buscar(Long.parseLong(id));
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

}
