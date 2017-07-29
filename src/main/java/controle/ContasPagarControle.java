package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.BaixaContasPagar;
import entidade.ContasPagar;
import entidade.PessoaJuridica;
import facade.ContasPagarFacade;
import facade.PessoaJuridicaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
    
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    
    private String id;

    private ContasPagar contasPagar;
    
    private BaixaContasPagar baixaContasPagar;
    
    private MoneyConverter moneyConverter;
    
    private ConverterGenerico converterPessoa;

    public List<ContasPagar> listaTodos() {
        return contasPagarFacade.listaTodos();
    }
    
    public List<PessoaJuridica> listaPessoasJuridica(String parte) {
        return pessoaJuridicaFacade.listaFiltrando(parte, "nome");
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
    
    public ConverterGenerico getConverterPessoa() {
        if (converterPessoa == null) {
            converterPessoa = new ConverterGenerico(pessoaJuridicaFacade);
        }
        return converterPessoa;
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
        Double valor = contasPagar.getValorBaixado() + baixaContasPagar.getValor();
        if(valor <= contasPagar.getValor()) {
            baixaContasPagar.setContasPagar(contasPagar);
            contasPagar.getListaBaixa().add(baixaContasPagar);
            baixaContasPagar = new BaixaContasPagar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Valor baixado é maior que o valor da parcela!"));
        }
        
        
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
