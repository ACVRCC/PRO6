package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/simplews")
public class SimpleService {

	@GET
    @Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    public String getMessage() {
        //TODO return proper representation object
        return "Simple!";
    }
	
	
}
