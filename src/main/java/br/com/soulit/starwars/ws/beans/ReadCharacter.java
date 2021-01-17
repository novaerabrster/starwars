package br.com.soulit.starwars.ws.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.bo.beans.ICharacterService;
import br.com.soulit.starwars.commons.ResultNotFoundException;
import br.com.soulit.starwars.ws.response.Character;

@Service
public class ReadCharacter extends AbstractRestService<Long, Character> implements IReadCharacter {

    private static final long serialVersionUID = 2880861015917284410L;
                                               
    @Autowired
    private ICharacterService characterService;
                              
    @Override
    protected Character process(Long in) throws Exception {
        final Character character = characterService.readCharacter(in);
        if (character == null) {
            throw new ResultNotFoundException("No character with id " + in + " found");
        }
        return character;
    }
}
