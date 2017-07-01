package controle;

import converter.EstadoConverter;
import enetidade.Cidade;
import enetidade.Estado;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CidadeControle {
    
    private Cidade cidade = new Cidade();
    private EstadoConverter estadoConverter = new EstadoConverter();
    private List<Cidade> lista = new ArrayList<>();
    
    public EstadoConverter getEstadoConverter() {
        if(estadoConverter == null) {
            estadoConverter = new EstadoConverter();
        }
        
        return estadoConverter;
    }
    
    public void setEstadoconverter(EstadoConverter estadoConverter) {
        this.estadoConverter = estadoConverter;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getLista() {
        return lista;
    }

    public void setLista(List<Cidade> lista) {
        this.lista = lista;
    }
    
    public void salvar() {
        lista.add(cidade);
    }

    public void novo() {
        cidade = new Cidade();
    }

    public void excluir(Cidade c) {
        lista.remove(c);
    }

    public void alterar(Cidade c) {
        this.cidade = c;
    }
    
}
