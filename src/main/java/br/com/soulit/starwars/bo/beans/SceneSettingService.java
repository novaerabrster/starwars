package br.com.soulit.starwars.bo.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.soulit.starwars.bo.helpers.WordCountHelper;
import br.com.soulit.starwars.bo.types.SceneSettingsSeparatorType;
import br.com.soulit.starwars.db.entity.SceneSettingCharWordDB;
import br.com.soulit.starwars.db.entity.SceneSettingDB;
import br.com.soulit.starwars.db.repository.ISceneSettingRepository;
import br.com.soulit.starwars.ws.response.Character;
import br.com.soulit.starwars.ws.response.SceneSettings;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SceneSettingService implements ISceneSettingService {

    private static final long       serialVersionUID = -7178454968138521736L;
                                                     
    @Autowired
    private ISceneSettingRepository repository;
                                    
    @Autowired
    private ISceneCharWordService   sceneCharWordService;
                                    
    @Autowired
    private WordCountHelper         helper;
                                    
    @Override
    public SceneSettingDB get(final List<String> text, final List<Integer> settings, int nameIndex)
            throws ExecutionException {
        final String name = formatSceneName(text.get(settings.get(nameIndex)));
        SceneSettingDB ss = repository.getByName(name);
        if (ss == null) {
            ss = repository.save(createSceneSetting(name));
        }
        return ss;
    }
    
    @Override
    public List<SceneSettings> listAllSettings() throws ExecutionException {
        final List<SceneSettingDB> all = repository.findAll();
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        return convertSceneSettings(all);
    }
    
    @Override
    public SceneSettings readSceneSetting(Long id) throws ExecutionException {
        final SceneSettingDB ssDB = repository.find(id);
        return convertSceneSetting(ssDB);
    }
    
    private SceneSettingDB createSceneSetting(String sceneName) {
        final SceneSettingDB sc = new SceneSettingDB();
        sc.setName(sceneName);
        return sc;
    }

    private String formatSceneName(String sceneName) {
        if (StringUtils.isEmpty(sceneName)) {
            return null;
        }
        sceneName = StringUtils.replace(sceneName, SceneSettingsSeparatorType.EXT.getSeparator(), StringUtils.EMPTY);
        sceneName = StringUtils.replace(sceneName, SceneSettingsSeparatorType.INT.getSeparator(), StringUtils.EMPTY);
        sceneName = StringUtils.replace(sceneName, SceneSettingsSeparatorType.SLASH.getSeparator(), StringUtils.EMPTY);
        final String[] split = StringUtils.split(sceneName, SceneSettingsSeparatorType.DASH.getSeparator());
        return StringUtils.trim(split[0]);
    }
    
    private List<SceneSettings> convertSceneSettings(final List<SceneSettingDB> all) throws ExecutionException {
        final List<SceneSettings> result = new ArrayList<>();
        for (final SceneSettingDB ss : all) {
            final SceneSettings setting = convertSceneSetting(ss);
            result.add(setting);
        }
        return result;
    }

    private SceneSettings convertSceneSetting(final SceneSettingDB ss) throws ExecutionException {
        if (ss == null) {
            return null;
        }
        final SceneSettings setting = new SceneSettings();
        setting.setId(ss.getId());
        setting.setName(ss.getName());
        setting.setCharacters(convertCharacterList(ss));
        return setting;
    }

    private List<Character> convertCharacterList(final SceneSettingDB ss) throws ExecutionException {
        final List<SceneSettingCharWordDB> sscwList = sceneCharWordService
                .findSceneSettingCharWordBySetting(ss.getId());
        if (CollectionUtils.isEmpty(sscwList)) {
            return null;
        }
        return helper.convertCharacters(sscwList);
    }
}
