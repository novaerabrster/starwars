package br.com.soulit.starwars.bo.beans;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import br.com.soulit.starwars.db.entity.CharacterDB;
import br.com.soulit.starwars.ws.response.Character;

public interface ICharacterService extends Serializable {

    CharacterDB get(List<String> text, int nameIndex);
    
    CharacterDB find(Long id);

    List<Character> listAllCharacters() throws ExecutionException;
    
    CharacterDB get(Long id) throws ExecutionException;
    
    Character readCharacter(Long id) throws ExecutionException;
}
