package facade;

import entidade.PessoaJuridica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PessoaJuridicaFacade extends AbstractFacade<PessoaJuridica>{

    @PersistenceContext(name = "aulajsfposjavaPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PessoaJuridicaFacade(){
        super(PessoaJuridica.class);
    }
}
