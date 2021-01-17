package br.com.soulit.starwars.test.bo.beans;

import java.io.FileNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.soulit.starwars.ws.beans.ISaveScript;

public class SaveScriptServiceTest extends AbstractStarWarsTestCase {

    @Autowired
    private ISaveScript service;

    @Test
    public void test() throws FileNotFoundException {
        final String text = getBaseText();
        service.getResponse(text);
    }
}
