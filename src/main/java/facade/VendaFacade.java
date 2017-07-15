package facade;

import entidade.ItensVenda;
import entidade.Produto;
import entidade.Venda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VendaFacade extends AbstractFacade<Venda>{

    @PersistenceContext(name = "aulajsfposjavaPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public VendaFacade(){
        super(Venda.class);
    }

    @Override
    public void salvar(Venda venda) {
        baixarEstoque(venda);
        em.merge(venda);
    }
    
    private void baixarEstoque(Venda venda){
        for(ItensVenda it: venda.getItensVendas()) {
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
        }
    }
    
}
