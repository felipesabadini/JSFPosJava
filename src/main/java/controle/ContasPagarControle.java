package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import converter.MoneyConverter;
import entidade.BaixaContasPagar;
import entidade.ContasPagar;
import facade.ContasPagarFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "listaContasPagar",
            pattern = "/contaspagar/listar",
            viewId = "/faces/estado/contaspagarlista.xhtml")
    ,
    @URLMapping(id = "novaContasPagar",
            pattern = "/contaspagar/novo",
            viewId = "/faces/contaspagar/contaspagaredita.xhtml")
    ,
    @URLMapping(id = "editaContasPagar",
            pattern = "/contaspagar/editar/#{estadoControle.id}",
            viewId = "/faces/contaspagar/contaspagaredita.xhtml")
})
public class ContasPagarControle implements Serializable {
    
    @EJB
    private ContasPagarFacade contasPagarFacade;
    
    private String id;

    private ContasPagar contasPagar;
    
    private BaixaContasPagar baixaContasPagar;
    
    private MoneyConverter moneyConverter;

    public List<ContasPagar> listaTodos() {
        return contasPagarFacade.listaTodos();
    }

    public void salvar() {
        contasPagarFacade.salvar(contasPagar);
    }

    @URLAction(mappingId = "novaContasPagar", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        contasPagar = new ContasPagar();
    }

    public void excluir(ContasPagar cp) {
        contasPagarFacade.remover(cp);
    }

    public void alterar(ContasPagar cp) {
        this.contasPagar = cp;
        baixaContasPagar = new BaixaContasPagar();
    }
    
    @URLAction(mappingId = "editaContasPagar", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void alterarPelaURL(){
        this.contasPagar = contasPagarFacade.buscar(Long.parseLong(id));
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaixaContasPagar getBaixaContasPagar() {
        return baixaContasPagar;
    }

    public void setBaixaContasPagar(BaixaContasPagar baixaContasPagar) {
        this.baixaContasPagar = baixaContasPagar;
    }

    public void baixar(){
        baixaContasPagar.setContasPagar(contasPagar);
        contasPagar.getListaBaixa().add(baixaContasPagar);
        baixaContasPagar = new BaixaContasPagar();
    }
    
    public void removerBaixa(BaixaContasPagar b){
        contasPagar.getListaBaixa().remove(b);
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

    public boolean getSituacaoPago(ContasPagar c) {
        if (Objects.equals(c.getValorBaixado(), c.getValor())) {
            return true;
        }
        return false;
    }

    public boolean getSituacaoAberto(ContasPagar c) {
        if (c.getValorBaixado() < c.getValor()) {
            return true;
        }
        return false;
    }
    
    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }
    
}
