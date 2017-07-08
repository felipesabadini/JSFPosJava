package facade;

import entidade.Pessoa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PessoaFacade extends AbstractFacade<Pessoa> {
    
    @PersistenceContext(name = "aulajsfposjavaPU")
    private EntityManager em;
    
    public PessoaFacade() {
        super(Pessoa.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
