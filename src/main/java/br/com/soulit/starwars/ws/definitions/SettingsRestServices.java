package br.com.soulit.starwars.ws.definitions;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.ws.beans.IReadSetting;
import br.com.soulit.starwars.ws.beans.ISearchSceneSettings;

@Service
@Path("/settings")
public class SettingsRestServices {

    @Autowired
    private ISearchSceneSettings search;
                                 
    @Autowired
    private IReadSetting  read;

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getSettings() {
        return search.getResponse(null);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getSettingById(@PathParam(value = "id") Long id) {
        return read.getResponse(id);
    }
}
