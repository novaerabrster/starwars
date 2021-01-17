package br.com.soulit.starwars.test.bo.beans;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.soulit.starwars.ws.beans.IReadCharacter;
import br.com.soulit.starwars.ws.beans.ISearchCharacters;
import br.com.soulit.starwars.ws.response.Status;

public class SearchAndReadCharactersTest extends SearchAndReadTests {
    
    @Autowired
    private ISearchCharacters search;
                              
    @Autowired
    private IReadCharacter    read;

    @Test
    public void listAllCharactersTest() {
        final Response response = search.getResponse(null);
        assertNotNull(response.getEntity());
        assertNotEquals(((List) response.getEntity()).size(), 0);
        assertTrue(((List) response.getEntity()).size() >= 0);
    }

    @Test
    public void readCharacterThatExists() {
        final Response response = read.getResponse(2l);
        assertNotNull(response.getEntity());
    }

    @Test
    public void readCharacterThatNotExists() {
        final Response response = read.getResponse(99l);
        assertNotNull(response.getEntity());
        assertTrue(response.getEntity() instanceof Status);
    }
}
