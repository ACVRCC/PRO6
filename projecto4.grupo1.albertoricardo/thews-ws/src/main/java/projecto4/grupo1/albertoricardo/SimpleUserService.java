package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("/Utilizador") //TUDO O QUE ESTÁ NESTA CLASSE FICA NO PATH /UTILIZADOR
public class SimpleUserService {

	@Inject
	private UserEJBLocal user;

	
	
	@GET
	@Path("/Find") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/Find
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<UserEntity> getAllUsers(){	
		return user.findAllUsers();
	}
	
	@GET
	@Path("/Count") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/Count
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public int getCountAllUsers(){	
		return user.countAllUsers();
	}
	
	//CONFIRMA QUE ISTO FUNCEMINA!!!!!!!
	@GET
	@Path("/List/{username}") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/List/email
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public UserEntity getUserByEmail(@PathParam ("username")String username){	
		return user.getUserEntity(username);
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
