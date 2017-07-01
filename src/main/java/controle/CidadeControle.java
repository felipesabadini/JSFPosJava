/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.EstadoConverter;
import enetidade.Cidade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author UniCesumar
 */
@ManagedBean
@SessionScoped
public class CidadeControle {

    private Cidade cidade = new Cidade();
    private List<Cidade> lista = new ArrayList<Cidade>();
    private EstadoConverter estadoConverter;

    public EstadoConverter getEstadoConverter() {
        if(estadoConverter == null){
            estadoConverter = new EstadoConverter();
        }
        return estadoConverter;
    }

    public void setEstadoConverter(EstadoConverter estadoConverter) {
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
