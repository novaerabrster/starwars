package br.com.soulit.starwars.db.repository;

import java.util.concurrent.ExecutionException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import br.com.soulit.starwars.db.entity.SceneSettingDB;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class SceneSettingsRepository extends AbstractRepository<SceneSettingDB> implements ISceneSettingRepository {

    private static final long                              serialVersionUID = -4331691495044477176L;
                                                                            
    private static final Log                               LOGGER           = LogFactory
            .getLog(SceneSettingsRepository.class);
            
    private LoadingCache<String, Optional<SceneSettingDB>> cache;
                                                           
    public SceneSettingsRepository() {
        super(SceneSettingDB.class);
    }

    @Override
    public SceneSettingDB getByName(String name) throws ExecutionException {
        return cache.get(name).orNull();
    }

    public SceneSettingDB findByName(String name) {
        final StringBuilder hql = new StringBuilder();
        hql.append("select ss from SceneSettingDB ss where ss.name = :name");
        final Query query = getEntityManager().createQuery(hql.toString());
        query.setParameter("name", name);
        try {
            return (SceneSettingDB) query.getSingleResult();
        }
        catch (final NoResultException nre) {
            LOGGER.info("SceneSetting with name " + name + " not found");
            return null;
        }
    }
    
    @Override
    public SceneSettingDB save(SceneSettingDB entity) {
        final SceneSettingDB saved = super.save(entity);
        cache.invalidate(saved.getName());
        return saved;
    }
    
    @Override
    protected void configCache() {
        cache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<String, Optional<SceneSettingDB>>() {

            @Override
            public Optional<SceneSettingDB> load(String name) {
                return Optional.fromNullable(findByName(name));
            }
        });
    }
}
