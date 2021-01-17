package br.com.soulit.starwars.test.bo.beans;

import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import br.com.soulit.starwars.ws.beans.ISaveScript;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-test.xml")
public class SearchAndReadTests extends AbstractStarWarsTestCase {

    @Autowired
    private ISaveScript service;

    @Before
    public void insertData() throws FileNotFoundException {
        final String text = getBaseText();
        service.getResponse(text);
    }
}
