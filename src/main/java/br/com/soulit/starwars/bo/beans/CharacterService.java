package br.com.soulit.starwars.bo.beans;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.bo.helpers.WordCountHelper;
import br.com.soulit.starwars.db.entity.CharacterDB;
import br.com.soulit.starwars.db.entity.dto.WordPerCharacterDTO;
import br.com.soulit.starwars.db.repository.ICharacterRepository;
import br.com.soulit.starwars.ws.response.Character;

@Service
public class CharacterService implements ICharacterService {
    
    private static final long    serialVersionUID = -1621133075791536347L;
                                                  
    @Autowired
    private ICharacterRepository repository;

    @Autowired
    private WordCountHelper      helper;
                                 
    @Override
    public CharacterDB get(List<String> text, int nameIndex) {
        final String name = StringUtils.trim(text.get(nameIndex));
        CharacterDB ch = repository.findByName(name);
        if (ch == null) {
            ch = repository.save(createCharacter(name));
        }
        return ch;
    }
    
    @Override
    public CharacterDB get(Long id) throws ExecutionException {
        return repository.get(id);
    }

    @Override
    public List<Character> listAllCharacters() throws ExecutionException {
        final List<WordPerCharacterDTO> wordsPerChar = repository.sumarizeWordsPerCharacter(null);
        if (CollectionUtils.isEmpty(wordsPerChar)) {
            return null;
        }
        return helper.convertCharacters(wordsPerChar);
    }
    
    @Override
    public Character readCharacter(Long id) throws ExecutionException {
        final List<WordPerCharacterDTO> wordsPerChar = repository.sumarizeWordsPerCharacter(id);
        if (CollectionUtils.isEmpty(wordsPerChar)) {
            return null;
        }
        return helper.convertCharacters(wordsPerChar).get(0);
    }

    @Override
    public CharacterDB find(Long id) {
        return repository.find(id);
    }

    private CharacterDB createCharacter(String name) {
        final CharacterDB ch = new CharacterDB();
        ch.setName(name);
        return ch;
    }
}
