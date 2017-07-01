/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import enetidade.Estado;
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
public class EstadoControle {

    private Estado estado = new Estado();
    private List<Estado> lista = new ArrayList<>();

    public List<Estado> getLista() {
        return lista;
    }

    public void setLista(List<Estado> lista) {
        this.lista = lista;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void salvar() {
        lista.add(estado);
    }

    public void novo() {
        estado = new Estado();
    }

    public void excluir(Estado e) {
        lista.remove(e);
    }

    public void alterar(Estado e) {
        this.estado = e;
    }

}
