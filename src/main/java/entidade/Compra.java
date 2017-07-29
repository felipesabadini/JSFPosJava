package entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    
    private Double valorTotal;
    
    private Integer numeroParcelas;
    
    @ManyToOne
    private PessoaJuridica pessoaJuridica;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compra", orphanRemoval = true)
    private List<ItensCompra> itensCompras;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compra", orphanRemoval = true)
    private List<ContasPagar> listaContasPagar;

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
    
    public List<ContasPagar> getListaContasPagar() {
        return listaContasPagar;
    }

    public void setListaContasPagar(List<ContasPagar> listaContasPagar) {
        this.listaContasPagar = listaContasPagar;
    }
    
    public Compra() {
        dataCompra = new Date();
        itensCompras = new ArrayList<>();
        listaContasPagar = new ArrayList<>();
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotal() {
        Double valor = 0d;
        for(ItensCompra it : itensCompras){
            valor = valor + it.getSubTotal();
        }
        this.valorTotal = valor;
        return valorTotal;
    }

    public Pessoa getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public List<ItensCompra> getItensCompras() {
        return itensCompras;
    }

    public void setItensCompras(List<ItensCompra> itensCompras) {
        this.itensCompras = itensCompras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Compra[ id=" + id + " ]";
    }

}
