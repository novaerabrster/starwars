package br.com.soulit.starwars.ws.beans;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.bo.beans.ICharacterService;
import br.com.soulit.starwars.commons.ResultNotFoundException;
import br.com.soulit.starwars.ws.response.Character;

@Service
public class SearchCharacters extends AbstractRestService<Object, List<Character>> implements ISearchCharacters {
    
    private static final long serialVersionUID = -8596804879778820446L;
                                               
    @Autowired
    private ICharacterService characterService;
                              
    @Override
    protected List<Character> process(Object in) throws Exception {
        final List<Character> all = characterService.listAllCharacters();
        if (CollectionUtils.isEmpty(all)) {
            throw new ResultNotFoundException("No characters found");
        }
        return all;
    }
}
