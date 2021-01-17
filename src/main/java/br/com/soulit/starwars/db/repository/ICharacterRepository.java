package br.com.soulit.starwars.db.repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import br.com.soulit.starwars.db.entity.CharacterDB;
import br.com.soulit.starwars.db.entity.dto.WordPerCharacterDTO;

public interface ICharacterRepository extends IRepository<CharacterDB> {

    CharacterDB findByName(String name);
    
    CharacterDB getByName(String name) throws ExecutionException;

    List<WordPerCharacterDTO> sumarizeWordsPerCharacter(Long characterId);
    
    CharacterDB get(Long id) throws ExecutionException;
}
