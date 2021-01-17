package br.com.soulit.starwars.bo.beans;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import br.com.soulit.starwars.db.entity.SceneSettingDB;
import br.com.soulit.starwars.ws.response.SceneSettings;

public interface ISceneSettingService extends Serializable {
    
    SceneSettingDB get(List<String> text, List<Integer> settings, int i) throws ExecutionException;

    List<SceneSettings> listAllSettings() throws ExecutionException;
    
    SceneSettings readSceneSetting(Long id) throws ExecutionException;
}
