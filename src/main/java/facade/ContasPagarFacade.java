package facade;

import entidade.ContasPagar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ContasPagarFacade extends AbstractFacade<ContasPagar> {

    @PersistenceContext(name = "aulajsfposjavaPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ContasPagarFacade(){
        super(ContasPagar.class);
    }
}
