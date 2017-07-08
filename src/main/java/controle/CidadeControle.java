package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import facade.EstadoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CidadeControle {

    private Cidade cidade = new Cidade();
    private List<Cidade> lista = new ArrayList<Cidade>();
    private ConverterGenerico estadoConverter;
    @EJB
    private EstadoFacade estadoFacade;
    

    public ConverterGenerico getEstadoConverter() {
        if(estadoConverter == null){
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public void setEstadoConverter(ConverterGenerico estadoConverter) {
        this.estadoConverter = estadoConverter;
    }

    public void testar() {
        System.out.println("Rsultado: " + cidade.getEstado().getNome());
    }

    public List<Cidade> getLista() {
        return lista;
    }

    public void setLista(List<Cidade> lista) {
        this.lista = lista;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void salvar() {
        lista.add(cidade);
    }

    public void novo() {
        cidade = new Cidade();
    }

    public void excluir(Cidade e) {
        lista.remove(e);
    }

    public void alterar(Cidade e) {
        this.cidade = e;
    }

}
