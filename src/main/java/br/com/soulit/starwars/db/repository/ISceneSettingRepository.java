package br.com.soulit.starwars.db.repository;

import java.util.concurrent.ExecutionException;
import br.com.soulit.starwars.db.entity.SceneSettingDB;

public interface ISceneSettingRepository extends IRepository<SceneSettingDB> {
    
    SceneSettingDB getByName(String name) throws ExecutionException;
}
