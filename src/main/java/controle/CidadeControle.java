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

    private Cidade cidade = new Cidade();
    private ConverterGenerico estadoConverter;
    @EJB
    private EstadoFacade estadoFacade;
    @EJB
    private CidadeFacade cidadeFacade;

    public ConverterGenerico getEstadoConverter() {
        if(estadoConverter == null){
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public void setEstadoConverter(ConverterGenerico estadoConverter) {
        this.estadoConverter = estadoConverter;
    }

    public List<Cidade> getListaTodos() {
        return cidadeFacade.listaTodos();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void salvar() {
        cidadeFacade.salvar(cidade);
    }

    public void novo() {
        cidade = new Cidade();
    }

    public void excluir(Cidade cid) {
        cidadeFacade.remover(cid);
    }

    public void alterar(Cidade cid) {
        this.cidade = cid;
    }

}
