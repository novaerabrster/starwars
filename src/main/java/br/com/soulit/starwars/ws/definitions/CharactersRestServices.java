package br.com.soulit.starwars.ws.definitions;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.ws.beans.IReadCharacter;
import br.com.soulit.starwars.ws.beans.ISearchCharacters;

@Service
@Path("/characters")
public class CharactersRestServices {

    @Autowired
    private ISearchCharacters search;

    @Autowired
    private IReadCharacter    read;

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getCharacters() {
        return search.getResponse(null);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getCharactersById(@PathParam(value = "id") Long id) {
        return read.getResponse(id);
    }
}
