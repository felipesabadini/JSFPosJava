package facade;

import entidade.Cidade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CidadeFacade extends AbstractFacade<Cidade> {
    @PersistenceContext(name = "aulajsfposjavaPU")
    private EntityManager em;

    public CidadeFacade() {
        super(Cidade.class);
    }
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
