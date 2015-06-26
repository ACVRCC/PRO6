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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("/Utilizador") //TUDO O QUE ESTÁ NESTA CLASSE FICA NO PATH /UTILIZADOR
public class SimpleUserService {

	@Inject
	private UserEJBLocal user;
	@Inject
	private PlaylistEJBLocal playlist;
	
	
	@GET
	@Path("/User/Find") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/List/Find
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<UserEntity> getAllUsers(){	
		return user.findAllUsers();
	}
	
	@GET
	@Path("/User/Count") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/List/Count
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public int getCountAllUsers(){	
		return user.countAllUsers();
	}
	
	//CONFIRMA QUE ISTO FUNCEMINA!!!!!!!
	@GET
	@Path("/User/List") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/List/List
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public UserEntity getUserByEmail(String username){	
		return user.getUserEntity(username);
	}
	@GET
	@Path("/Playlist/Count") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/Playlist/Count
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public int  getCountPlaylists(){
		return playlist.getPlaylists().size();
	}
	@GET
	@Path("/Playlist/Find") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/Playlist/Find
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public List<PlaylistEntity> getAllPlaylist(){
		return  playlist.getPlaylists();
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
