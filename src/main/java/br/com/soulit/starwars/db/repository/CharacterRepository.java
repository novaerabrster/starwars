package br.com.soulit.starwars.db.repository;

import java.util.List;
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
import br.com.soulit.starwars.db.entity.CharacterDB;
import br.com.soulit.starwars.db.entity.dto.WordPerCharacterDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class CharacterRepository extends AbstractRepository<CharacterDB> implements ICharacterRepository {

    private static final long                           serialVersionUID = -9078333070388205631L;
                                                                         
    private static final Log                            LOGGER           = LogFactory.getLog(CharacterRepository.class);
                                                                         
    private LoadingCache<String, Optional<CharacterDB>> nameCache;

    private LoadingCache<Long, Optional<CharacterDB>>   idCache;
                                                        
    public CharacterRepository() {
        super(CharacterDB.class);
    }
    
    @Override
    public CharacterDB findByName(String name) {
        final StringBuilder hql = new StringBuilder();
        hql.append("select ch from CharacterDB ch where ch.name = :name");
        final Query query = getEntityManager().createQuery(hql.toString());
        query.setParameter("name", name);
        try {
            return (CharacterDB) query.getSingleResult();
        }
        catch (final NoResultException nre) {
            LOGGER.info("CharacterDB with name " + name + " not found");
            return null;
        }
    }

    @Override
    public List<WordPerCharacterDTO> sumarizeWordsPerCharacter(Long characterId) {
        final StringBuilder hql = new StringBuilder();
        hql.append(
                " select new br.com.soulit.starwars.db.entity.dto.WordPerCharacterDTO( ch.id, sscw.id.word, sum(sscw.count) ) ");
        hql.append("   from SceneSettingCharWordDB sscw ");
        hql.append("      , CharacterDB ch ");
        hql.append("  where sscw.id.characterId = ch.id ");
        if (characterId != null) {
            hql.append(" and ch.id = :characterId ");
        }
        hql.append("  group by ch.name, sscw.id.word ");
        hql.append("  order by ch.name, sscw.id.word desc ");
        final Query query = getEntityManager().createQuery(hql.toString());
        if (characterId != null) {
            query.setParameter("characterId", characterId);
        }
        return query.getResultList();
    }

    @Override
    public CharacterDB getByName(String name) throws ExecutionException {
        return nameCache.get(name).orNull();
    }

    @Override
    public CharacterDB get(Long id) throws ExecutionException {
        return idCache.get(id).orNull();
    }
    
    @Override
    public CharacterDB save(CharacterDB entity) {
        final CharacterDB saved = super.save(entity);
        nameCache.invalidate(saved.getName());
        idCache.invalidate(saved.getId());
        return saved;
    }
    
    @Override
    protected void configCache() {
        nameCache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<String, Optional<CharacterDB>>() {

            @Override
            public Optional<CharacterDB> load(String name) {
                return Optional.fromNullable(findByName(name));
            }
        });
        idCache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<Long, Optional<CharacterDB>>() {

            @Override
            public Optional<CharacterDB> load(Long id) {
                return Optional.fromNullable(find(id));
            }
        });
    }
}
