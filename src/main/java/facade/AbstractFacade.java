package facade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class AbstractFacade<T> implements Serializable {
    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();
    
    public void salvar(T entity) {
        getEntityManager().merge(entity);
    }
    
    public void remover(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    public T buscar(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public List<T> listaTodos() {
        Query q = getEntityManager().createQuery("from " + entityClass.getSimpleName());
        return q.getResultList();
    }
    
}