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
public class ContasReceber implements Serializable, BaseEntidade {

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
    private Venda venda;
    
    @ManyToOne
    private Pessoa pessoa;
    
    @OneToMany(cascade = CascadeType.ALL, 
            fetch = FetchType.EAGER, 
            mappedBy = "contasReceber", 
            orphanRemoval = true)
    private List<BaixaContasReceber> listaBaixa;

    public Double getValorBaixado(){
        Double total = 0d;
        for(BaixaContasReceber b : listaBaixa){
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

    public List<BaixaContasReceber> getListaBaixa() {
        return listaBaixa;
    }

    public void setListaBaixa(List<BaixaContasReceber> listaBaixa) {
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
        if (!(object instanceof ContasReceber)) {
            return false;
        }
        ContasReceber other = (ContasReceber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ContasReceber[ id=" + id + " ]";
    }

}
