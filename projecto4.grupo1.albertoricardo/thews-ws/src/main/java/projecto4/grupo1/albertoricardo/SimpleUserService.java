package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("/Utilizador") //TUDO O QUE ESTÁ NESTA CLASSE FICA NO PATH /UTILIZADOR
public class SimpleUserService {

	@Inject
	private UserEJBLocal user;
	
	@GET
	@Path("/List") //ACEDER ATRAVÉS DO PATH UTILIZADOR/LIST
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<UserEntity> getAllUsers(){	
		return user.findAllUsers();
	}
	
	
	
//	@POST
//	@Path("/simpleuser")
//	@Consumes({MediaType.APPLICATION_XML})
//	@Produces({MediaType.APPLICATION_XML})
//	public Response createSimpleUser(SimpleUser user){
//		System.out.println(user.getId());
//		System.out.println(user.getUsername());
//		SimpleUser another = new SimpleUser();
//		another.setUsername(user.getUsername());// Why ? :(
//		
//		
//		SimpleUser newuser = usermng.create(another);
//		//Response.notModified();
//		
//		return Response.ok(newuser).build();
//		
//	}
	
}
