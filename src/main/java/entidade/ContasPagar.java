package entidade;

import java.io.Serializable;
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
public class ContasPagar implements Serializable, BaseEntidade {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;
    
    @Temporal(TemporalType.DATE)
    private Date vencimento;
    
    private Double valor;
    
    private Integer parcela;
    
    @ManyToOne
    private Compra compra;
    
    @ManyToOne
    private Pessoa pessoa;
    
    @OneToMany(cascade = CascadeType.ALL, 
            fetch = FetchType.EAGER, 
            mappedBy = "contasPagar", 
            orphanRemoval = true)
    private List<BaixaContasPagar> listaBaixa;

    public Double getValorBaixado(){
        Double total = 0d;
        for(BaixaContasPagar b : listaBaixa){
            total += b.getValor();
        }
        return total;
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public List<BaixaContasPagar> getListaBaixa() {
        return listaBaixa;
    }

    public void setListaBaixa(List<BaixaContasPagar> listaBaixa) {
        this.listaBaixa = listaBaixa;
    }

    
    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    @Override
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
        if (!(object instanceof ContasPagar)) {
            return false;
        }
        ContasPagar other = (ContasPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ContasPagar[ id=" + id + " ]";
    }

}
