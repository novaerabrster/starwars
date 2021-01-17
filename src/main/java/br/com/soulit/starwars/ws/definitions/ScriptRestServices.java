package br.com.soulit.starwars.ws.definitions;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.ws.beans.ISaveScript;

@Service
@Path("/script")
public class ScriptRestServices {
    
    @Autowired
    private ISaveScript saveService;
    
    @POST
    @Path("/")
    @Consumes("text/plain")
    @Produces("application/json")
    public Response saveScript(String text) {
        return saveService.getResponse(text);
    }
}
