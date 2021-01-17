package br.com.soulit.starwars.bo.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.db.entity.CharacterDB;
import br.com.soulit.starwars.db.entity.SceneSettingCharWordDB;
import br.com.soulit.starwars.db.entity.SceneSettingCharWordPK;
import br.com.soulit.starwars.db.entity.SceneSettingDB;
import br.com.soulit.starwars.db.repository.ISceneCharWordRepository;

@Service
public class SceneCharWordService implements ISceneCharWordService {

    private static final String      WORD_SELECT_REGEX = "((\\w+\\'\\w+)|(\\w+))";

    private static final long        serialVersionUID  = -3127626023057307654L;

    @Autowired
    private ISceneCharWordRepository repository;

    @Override
    public void countWords(SceneSettingDB ss, CharacterDB ch, List<String> words) {
        if (ss == null || ch == null || CollectionUtils.isEmpty(words)) {
            return;
        }
        final Map<SceneSettingCharWordPK, SceneSettingCharWordDB> mappedWordCounter = new HashMap<>();
        for (final String word : words) {
            final SceneSettingCharWordPK pk = createPK(ss, ch, word);
            SceneSettingCharWordDB sscw = mappedWordCounter.get(pk);
            if (sscw == null) {
                sscw = new SceneSettingCharWordDB();
                sscw.setId(pk);
                sscw.initCounter();
            } else {
                sscw.addCounter();
            }
            mappedWordCounter.put(pk, sscw);
        }
        if (mappedWordCounter == null || mappedWordCounter.size() <= 0) {
            return;
        }
        for (final SceneSettingCharWordDB sscw : mappedWordCounter.values()) {
            repository.save(sscw);
        }
    }

    @Override
    public List<String> getWordList(final List<String> text, final List<Integer> speechByChar) {
        final List<String> wordList = new ArrayList<>();
        for (final Integer speechIndex : speechByChar) {
            final String line = text.get(speechIndex);
            final Pattern pattern = Pattern.compile(WORD_SELECT_REGEX);
            final Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                final String word = line.substring(matcher.start(), matcher.end());
                wordList.add(word.toLowerCase());
            }
        }
        return wordList;
    }
    
    public void save(Set<SceneSettingCharWordDB> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (final SceneSettingCharWordDB sscw : list) {
            repository.save(sscw);
        }
    }

    @Override
    public List<SceneSettingCharWordDB> findSceneSettingCharWordBySetting(Long settingId) {
        return repository.findSceneSettingCharWordBySetting(settingId);
    }

    private SceneSettingCharWordPK createPK(SceneSettingDB ss, CharacterDB ch, final String word) {
        final SceneSettingCharWordPK pk = new SceneSettingCharWordPK();
        pk.setCharacterId(ch.getId());
        pk.setSettingId(ss.getId());
        pk.setWord(word);
        return pk;
    }
}
