package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import converter.MoneyConverter;
import entidade.BaixaContasReceber;
import entidade.ContasReceber;
import facade.ContasReceberFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "listaContasReceber",
            pattern = "/contasreceber/listar",
            viewId = "/faces/contasreceber/contasreceberlista.xhtml")
    ,
    @URLMapping(id = "novaContasReceber",
            pattern = "/contasreceber/novo",
            viewId = "/faces/contasreceber/contasreceberedita.xhtml")
    ,
    @URLMapping(id = "editaContasReceber",
            pattern = "/contasreceber/editar/#{contasReceberControle.id}",
            viewId = "/faces/contasreceber/contasreceberedita.xhtml")
})
public class ContasReceberControle implements Serializable {
    
    @EJB
    private ContasReceberFacade contasReceberFacade;
    
    private String id;

    private ContasReceber contasReceber;
    
    private BaixaContasReceber baixaContasReceber;
    
    private MoneyConverter moneyConverter;

    public List<ContasReceber> listaTodos() {
        return contasReceberFacade.listaTodos();
    }

    public void salvar() {
        contasReceberFacade.salvar(contasReceber);
    }

    @URLAction(mappingId = "novaContasReceber", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        contasReceber = new ContasReceber();
    }

    public void excluir(ContasReceber e) {
        contasReceberFacade.remover(e);
    }

    public void alterar(ContasReceber e) {
        this.contasReceber = e;
        baixaContasReceber = new BaixaContasReceber();
    }
    
    @URLAction(mappingId = "editaContasReceber", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void alterarPelaURL(){
        this.contasReceber = contasReceberFacade.buscar(Long.parseLong(id));
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaixaContasReceber getBaixaContasReceber() {
        return baixaContasReceber;
    }

    public void setBaixaContasReceber(BaixaContasReceber baixaContasReceber) {
        this.baixaContasReceber = baixaContasReceber;
    }

    public void baixar(){
        baixaContasReceber.setContasReceber(contasReceber);
        contasReceber.getListaBaixa().add(baixaContasReceber);
        baixaContasReceber = new BaixaContasReceber();
    }
    
    public void removerBaixa(BaixaContasReceber b){
        contasReceber.getListaBaixa().remove(b);
    }
    
    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public boolean getSituacaoPago(ContasReceber c) {
        if (Objects.equals(c.getValorBaixado(), c.getValor())) {
            return true;
        }
        return false;
    }

    public boolean getSituacaoAberto(ContasReceber c) {
        if (c.getValorBaixado() < c.getValor()) {
            return true;
        }
        return false;
    }
    
    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }
    
}
