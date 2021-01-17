package br.com.soulit.starwars.db.repository;

import java.util.List;
import br.com.soulit.starwars.db.entity.SceneSettingCharWordDB;

public interface ISceneCharWordRepository extends IRepository<SceneSettingCharWordDB> {
    
    List<SceneSettingCharWordDB> findSceneSettingCharWordBySetting(Long settingId);
}
