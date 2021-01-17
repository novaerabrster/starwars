package br.com.soulit.starwars.db.repository;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import br.com.soulit.starwars.db.entity.SceneSettingCharWordDB;

@Repository
public class SceneCharWordRepository extends AbstractRepository<SceneSettingCharWordDB>
        implements ISceneCharWordRepository {
        
    private static final long serialVersionUID = 6755389004521935378L;

    public SceneCharWordRepository() {
        super(SceneSettingCharWordDB.class);
    }

    @Override
    public List<SceneSettingCharWordDB> findSceneSettingCharWordBySetting(Long settingId) {
        final StringBuilder hql = new StringBuilder();
        hql.append("select sscw from SceneSettingCharWordDB sscw where sscw.id.settingId = :settingId");
        final Query query = getEntityManager().createQuery(hql.toString());
        query.setParameter("settingId", settingId);
        return query.getResultList();
    }
}
