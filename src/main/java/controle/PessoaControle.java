package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.Pessoa;
import entidade.PessoaFisica;
import entidade.PessoaJuridica;
import facade.CidadeFacade;
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
    
    @EJB
    private CidadeFacade cidadeFacade;
    
    @ManagedProperty(value = "#{pessoaFisicaControle}")
    private PessoaFisicaControle pessoaFisicaControle;
    
    @ManagedProperty(value = "#{pessoaJuridicaControle}")
    private PessoaJuridicaControle pessoaJuridicaControle;
    
    private ConverterGenerico converterCidade;

    public String editar(Pessoa p) {
        if (p instanceof PessoaFisica) {
            pessoaFisicaControle.setPessoaFisica((PessoaFisica) p);
            return "pessoafisicaedita";
        } else {
            pessoaJuridicaControle.setPessoaJuridica((PessoaJuridica) p);
            return "pessoajuridicaedita";
        }
    }

    public List<Pessoa> listaTodos() {
        return pessoaFacade.listaTodos();
    }
    
    public List<Cidade> listaCidades(String parte) {
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

    public void excluir(Pessoa p) {
        if (p instanceof PessoaFisica) {
            pessoaFisicaControle.excluir((PessoaFisica) p);
        } else {
            pessoaJuridicaControle.excluir((PessoaJuridica) p);
        }
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

    public ConverterGenerico getConverterCidade() {
        if (converterCidade == null) {
            converterCidade = new ConverterGenerico(cidadeFacade);
        }
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterCidade) {
        this.converterCidade = converterCidade;
    } 

}
