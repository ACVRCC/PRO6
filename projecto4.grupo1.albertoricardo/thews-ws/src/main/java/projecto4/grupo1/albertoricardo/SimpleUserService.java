package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import projecto4.grupo1.albertoricardo.security.PasswordEncryptor;


@Stateless
@Path("/Utilizador") //TUDO O QUE ESTÁ NESTA CLASSE FICA NO PATH /UTILIZADOR
public class SimpleUserService {

	@Inject
	private UserEJBLocal user;
	@Inject 
	private MusicListEJBLocal music;
	
	
	@GET
	@Path("/Find") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/Find
	//Serviço nº2
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<UserEntity> getAllUsers(){	
		return user.findAllUsers();
	}
	
	@GET
	@Path("/Count") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/Count
	//Serviço nº1
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public int getCountAllUsers(){	
		return user.countAllUsers();
	}
	
	
	@GET
	@Path("/List/{username}") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/List/email
	//Serviço nº 3 Procurar por email
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public UserEntity getUserByEmail(@PathParam ("username")String username){	
		return user.getUserEntity(username);
	}


	
	@POST
	@Path("/Add")  //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/Add
	@Consumes({MediaType.APPLICATION_XML})
//	@Produces({MediaType.APPLICATION_XML})
	//Serviço nº 14 Adiciona Utilizador
		public Response createUser(UserEntity u){
		 user.registerNewUser(u);
		return Response.status(200).build();
		}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_XML)
	//Serviço nº 14 remover Utilizador
	public Response removeUserById(@PathParam("id") int id){	
		UserEntity u = user.getUserFromId(id);		
		boolean removed = user.deleteUser(user.getUserFromId(id));
		if (removed)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}
	
	@POST
	@Path("Change/{id}/{password}")
//	@Consumes({MediaType.APPLICATION_XML})
	//Serviço nº 15
	public void changePw(@PathParam("id") int id, @PathParam("password") String password){
		UserEntity u=user.getUserFromId(id);
		PasswordEncryptor pe = new PasswordEncryptor();
		String ePassword = pe.encrypt(password);
		u.setPassword(ePassword);		
	}
	
	//Ainda não funciona
//	@GET
//	@Path("DeleteMusic/{iduser}/{idmusic}")
//	//Serviço nº 16
//	public void deleteMusic(@PathParam("iduser") int iduser, @PathParam("idmusic") int idmusic){
//		UserEntity u=user.getUserFromId(iduser);
//		MusicEntity m = music.listOwnMusics(u).get(idmusic);
//		music.removerMusicUserOwnership (m,u);
		
//	}
	//APAGAR VEIO DO EXEMPLO DO MONITOR
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
