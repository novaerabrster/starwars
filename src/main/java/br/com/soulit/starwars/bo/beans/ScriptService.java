package br.com.soulit.starwars.bo.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.bo.helpers.ListFilterHelper;
import br.com.soulit.starwars.bo.types.SceneSettingsSeparatorType;
import br.com.soulit.starwars.db.entity.CharacterDB;
import br.com.soulit.starwars.db.entity.SceneSettingDB;
import br.com.soulit.starwars.db.entity.ScriptDB;
import br.com.soulit.starwars.db.repository.IScriptRepository;

@Service
public class ScriptService implements IScriptService {
    
    private static final long     serialVersionUID      = -7135135086478532690L;

    private static final Log      LOGGER                = LogFactory.getLog(ScriptService.class);
                                                        
    private static final String   SPEECH_INDENT         = "          ";
                                                        
    private static final String   CHARACTER_NAME_INDENT = "                      ";
                                                        
    @Autowired
    private ListFilterHelper      helper;

    @Autowired
    private ISceneSettingService  sceneSettingService;

    @Autowired
    private ICharacterService     characterService;

    @Autowired
    private ISceneCharWordService sceneCharWordService;

    @Autowired
    private IScriptRepository     repository;

    @Override
    public ScriptDB find(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        LOGGER.info(text.hashCode());
        return repository.find(text.hashCode());
    }
    
    @Override
    public void save(String text) {
        if (StringUtils.isEmpty(text)) {
            return;
        }
        repository.save(createScript(text));
    }

    @Override
    public void parse(String script) throws ExecutionException {
        final List<String> text = Arrays.asList(script.split("\n"));
        final List<Integer> settings = new ArrayList<>();
        final List<Integer> characters = new ArrayList<>();
        final List<Integer> speech = new ArrayList<>();
        findIndexes(text, settings, characters, speech);
        for (int i = 0; i < settings.size(); i++) {
            final SceneSettingDB ss = sceneSettingService.get(text, settings, i);
            final List<Integer> filteredCharacters = helper.filterList(settings, characters, i);
            final List<Integer> filteredSpeech = helper.filterList(settings, speech, i);
            for (int j = 0; j < filteredCharacters.size(); j++) {
                final CharacterDB ch = characterService.get(text, filteredCharacters.get(j));
                final List<Integer> speechByChar = helper.filterList(filteredCharacters, filteredSpeech, j);
                LOGGER.info(speechByChar);
                sceneCharWordService.countWords(ss, ch, sceneCharWordService.getWordList(text, speechByChar));
            }
        }
    }
    
    private void findIndexes(List<String> text, List<Integer> sceneSettings, List<Integer> characters,
            List<Integer> speech) {
        for (int i = 0; i < text.size(); i++) {
            final String line = text.get(i);
            if (line.contains(SceneSettingsSeparatorType.INT.getSeparator())
                    || line.contains(SceneSettingsSeparatorType.EXT.getSeparator())
                    || line.contains(SceneSettingsSeparatorType.FULL.getSeparator())) {
                sceneSettings.add(i);
            } else if (line.contains(CHARACTER_NAME_INDENT) && matchCharacter(line)) {
                characters.add(i);
            } else if (line.contains(SPEECH_INDENT) && matchSpeech(line)) {
                speech.add(i);
            }
        }
        LOGGER.info("SCENE count: " + sceneSettings.size());
        LOGGER.info("VALUES: " + sceneSettings);
        LOGGER.info("CHARACTER count: " + characters.size());
        LOGGER.info("VALUES: " + characters);
        LOGGER.info("SPEECH count: " + speech.size());
        LOGGER.info("VALUES: " + speech);
        Collections.sort(sceneSettings);
        Collections.sort(characters);
        Collections.sort(speech);
    }

    private boolean matchCharacter(String input) {
        final Pattern pattern = Pattern.compile("(\\s{22})([A-Z])");
        final Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private boolean matchSpeech(String input) {
        final Pattern pattern = Pattern.compile("(\\s{10})([A-Za-z])");
        final Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private ScriptDB createScript(String text) {
        final ScriptDB sc = new ScriptDB();
        sc.setId(text.hashCode());
        // sc.setText(text);
        return sc;
    }
}
