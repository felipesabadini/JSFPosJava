package controle;

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
    private Estado estado = new Estado();
    
    private List<Cidade> lista = new ArrayList<>();

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        estado = new Estado();
    }

    public void excluir(Cidade c) {
        lista.remove(c);
    }

    public void alterar(Cidade c) {
        this.cidade = c;
    }
    
}
