package br.com.soulit.starwars.test.bo.beans;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import br.com.soulit.starwars.ws.beans.IReadSetting;
import br.com.soulit.starwars.ws.beans.ISearchSceneSettings;
import br.com.soulit.starwars.ws.response.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-test.xml")
public class SearchAndReadSceneSettingsTest extends SearchAndReadTests {

    @Autowired
    private ISearchSceneSettings search;
                                 
    @Autowired
    private IReadSetting         read;
                                 
    @Test
    public void listAllSettingsTest() {
        final Response response = search.getResponse(null);
        assertNotNull(response.getEntity());
        assertNotEquals(((List) response.getEntity()).size(), 0);
        assertTrue(((List) response.getEntity()).size() >= 0);
    }

    @Test
    public void readSettingThatExists() {
        final Response response = read.getResponse(2l);
        assertNotNull(response.getEntity());
    }

    @Test
    public void readSettingThatNotExists() {
        final Response response = read.getResponse(99l);
        assertNotNull(response.getEntity());
        assertTrue(response.getEntity() instanceof Status);
    }
}
