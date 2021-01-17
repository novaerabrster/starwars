package br.com.soulit.starwars.ws.beans;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.soulit.starwars.commons.ResultNotFoundException;
import br.com.soulit.starwars.commons.StarWarsException;
import br.com.soulit.starwars.ws.response.Status;

public abstract class AbstractRestService<IN, OUT> implements IRestService<IN, OUT> {
    
    private static final long serialVersionUID = 3137656865927034357L;
                                               
    protected OUT             response;
                              
    private static final Log  LOGGER           = LogFactory.getLog(AbstractRestService.class);
                                               
    @Override
    public Response getResponse(IN in) {
        final ResponseBuilder rb = Response.ok();
        try {
            final OUT out = process(in);
            rb.entity(out);
        }
        catch (final ResultNotFoundException rnfEx) {
            rb.status(Response.Status.NOT_FOUND);
            handleException(rb, rnfEx.getMessage());
        }
        catch (final StarWarsException swEx) {
            handleException(rb, swEx.getMessage());
        }
        catch (final Exception e) {
            LOGGER.error(ExceptionUtils.getMessage(e), e);
            handleException(rb, "Unexpected error.");
        }
        return rb.build();
    }
    
    private void handleException(ResponseBuilder rb, String message) {
        final Status status = new Status();
        status.setMessage(message);
        rb.entity(status);
    }
    
    protected abstract OUT process(IN in) throws Exception;
}
