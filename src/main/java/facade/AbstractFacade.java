package facade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class AbstractFacade<T> implements Serializable{
    
    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entityClass){
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();
    
    public void salvar(T entity){
        getEntityManager().merge(entity);
    }
    
    public void remover(T entity){
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    public T buscar(Object id){
        return getEntityManager().find(entityClass, id);
    }
    
    public List<T> listaTodos(){
        Query q = getEntityManager().createQuery("from " + entityClass.getSimpleName());
        return q.getResultList();
    }
    
    public List<T> listaFiltrando(String filtro, String... atributos) {
        String hql = "from " + entityClass.getSimpleName() + " obj where ";
        for(String atributo: atributos) {
            hql += "lower(obj." + atributo + ") like :param or "; 
        }
        hql = hql.substring(0, hql.length() - 3);
        
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("param", "%" + filtro.toLowerCase() + "%");
        return q.getResultList();
    }
    
}
