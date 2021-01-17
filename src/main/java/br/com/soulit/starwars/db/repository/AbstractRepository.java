package br.com.soulit.starwars.db.repository;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class AbstractRepository<ENTITY> implements IRepository<ENTITY> {
    
    private static final long serialVersionUID = -1543286986788548489L;
                                               
    protected Class<ENTITY>   clazz;
                              
    public AbstractRepository(Class<ENTITY> clazz) {
        super();
        this.clazz = clazz;
    }
    
    @PostConstruct
    private void initCache() {
        configCache();
    }

    protected void configCache() { /*implement if necessary*/ };

    @PersistenceContext(name = "PU1")
    private EntityManager em;
    
    @Override
    public ENTITY save(ENTITY entity) {
        return em.merge(entity);
    }

    @Override
    public List<ENTITY> findAll() {
        final StringBuilder sb = new StringBuilder();
        sb.append("select obj from ").append(clazz.getSimpleName()).append(" obj ");
        final Query query = em.createQuery(sb.toString());
        return query.getResultList();
    }

    @Override
    public ENTITY find(Serializable id) {
        return em.find(clazz, id);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
}
