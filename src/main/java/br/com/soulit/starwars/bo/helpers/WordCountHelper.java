package br.com.soulit.starwars.bo.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.soulit.starwars.bo.beans.ICharacterService;
import br.com.soulit.starwars.db.entity.CharacterDB;
import br.com.soulit.starwars.db.entity.SceneSettingCharWordDB;
import br.com.soulit.starwars.db.entity.dto.WordPerCharacterDTO;
import br.com.soulit.starwars.ws.response.Character;
import br.com.soulit.starwars.ws.response.WordCount;

@Component
public class WordCountHelper {
    
    @Autowired
    private ICharacterService characterService;
    
    public List<Character> convertCharacters(List objs) throws ExecutionException {
        Long characterId;
        String word;
        Long count;
        if (CollectionUtils.isEmpty(objs)) {
            return null;
        }
        final Map<Character, List<WordCount>> mappedWords = new HashMap<>();
        for (final Object obj : objs) {
            if (obj instanceof SceneSettingCharWordDB) {
                characterId = ((SceneSettingCharWordDB) obj).getId().getCharacterId();
                word = ((SceneSettingCharWordDB) obj).getId().getWord();
                count = ((SceneSettingCharWordDB) obj).getCount();
            } else {
                characterId = ((WordPerCharacterDTO) obj).getId();
                word = ((WordPerCharacterDTO) obj).getWord();
                count = ((WordPerCharacterDTO) obj).getCount();
            }
            final Character ch = convertCharacter(characterId);
            updateWordCount(mappedWords, word, count, ch);
        }
        return buildCharacterList(mappedWords);
    }

    private void updateWordCount(final Map<Character, List<WordCount>> mappedWords, String word, Long count,
            final Character ch) {
        List<WordCount> words = mappedWords.get(ch);
        if (CollectionUtils.isEmpty(words)) {
            words = new ArrayList<>();
        }
        final WordCount wc = convertWordCount(word, count);
        words.add(wc);
        mappedWords.put(ch, words);
    }
    
    private Character convertCharacter(Long characterId) throws ExecutionException {
        final CharacterDB chDB = characterService.get(characterId);
        final Character ch = new Character();
        ch.setId(chDB.getId());
        ch.setName(chDB.getName());
        return ch;
    }

    private WordCount convertWordCount(String word, Long count) {
        final WordCount wc = new WordCount();
        wc.setWord(word);
        wc.setCount(count);
        return wc;
    }

    private List<Character> buildCharacterList(final Map<Character, List<WordCount>> mappedWords) {
        final List<Character> characters = new ArrayList<>();
        for (final Entry<Character, List<WordCount>> entry : mappedWords.entrySet()) {
            final Character key = entry.getKey();
            if (key == null) {
                continue;
            }
            final List<WordCount> words = entry.getValue();
            if (CollectionUtils.isNotEmpty(words)) {
                Collections.sort(words);
                if (words.size() > 10) {
                    key.setWordCounts(words.subList(0, 10));
                } else {
                    key.setWordCounts(words);
                }
            }
            characters.add(key);
        }
        return characters;
    }
}
