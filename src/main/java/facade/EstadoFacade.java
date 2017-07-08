package facade;

import entidade.Estado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstadoFacade extends AbstractFacade<Estado> {
    @PersistenceContext(name = "aulajsfposjavaPU")
    private EntityManager em;

    public EstadoFacade() {
        super(Estado.class);
    }
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
