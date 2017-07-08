package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import facade.CidadeFacade;
import facade.EstadoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CidadeControle {

    private Cidade cidade;
    private ConverterGenerico estadoConverter;
    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private EstadoFacade estadoFacade;

    public void testar() {
        System.out.println("Rsultado: " + cidade.getEstado().getNome());
    }

    public List<Cidade> listaTodos() {
        return cidadeFacade.listaTodos();
    }
    
    public void salvar() {
        cidadeFacade.salvar(cidade);
    }

    public void novo() {
        cidade = new Cidade();
    }

    public void excluir(Cidade c) {
        cidadeFacade.remover(c);
    }

    public void alterar(Cidade e) {
        this.cidade = e;
    }
    
    public ConverterGenerico getEstadoConverter() {
        if(estadoConverter == null){
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public void setEstadoConverter(ConverterGenerico estadoConverter) {
        this.estadoConverter = estadoConverter;
    }
    
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
