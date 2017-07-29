package facade;

import entidade.Compra;
import entidade.ItensCompra;
import entidade.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CompraFacade extends AbstractFacade<Compra> {

    @PersistenceContext(name = "aulajsfposjavaPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public CompraFacade(){
        super(Compra.class);
    }

    @Override
    public List<Compra> listaTodos() {
        List<Compra> compras = super.listaTodos();
        for(Compra c : compras){
            c.getItensCompras().size();
            c.getListaContasPagar().size();
        }
        return compras;
    }
    

    @Override
    public void salvar(Compra compra) {
        somarEstoque(compra);   
        em.merge(compra);
    }

    private void somarEstoque(Compra compra) {
        for(ItensCompra it : compra.getItensCompras()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque() + it.getQuantidade());
            em.merge(p);
        }
    }
    
}
