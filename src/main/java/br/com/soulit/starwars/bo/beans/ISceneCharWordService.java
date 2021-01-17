package br.com.soulit.starwars.bo.beans;

import java.io.Serializable;
import java.util.List;
import br.com.soulit.starwars.db.entity.CharacterDB;
import br.com.soulit.starwars.db.entity.SceneSettingCharWordDB;
import br.com.soulit.starwars.db.entity.SceneSettingDB;

public interface ISceneCharWordService extends Serializable {
    
    void countWords(SceneSettingDB ss, CharacterDB ch, List<String> words);

    List<String> getWordList(List<String> text, List<Integer> speechByChar);
    
    List<SceneSettingCharWordDB> findSceneSettingCharWordBySetting(Long settingId);
}
