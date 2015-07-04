package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import projecto4.grupo1.albertoricardo.entities.UserEntity;
import projecto4.grupo1.albertoricardo.security.PasswordEncryptor;

@Stateless
@Path("/Utilizador")
// TUDO O QUE ESTÁ NESTA CLASSE FICA NO PATH /UTILIZADOR
public class SimpleUserService {

	@EJB
	UserEJBLocal user;
	@EJB
	MusicListEJBLocal musics;
	@EJB
	LoggedUsers logged;

	@GET
	@Path("/Find")
	// ACEDER ATRAVÉS DO PATH
	// http://localhost:8080/thews-ws/rest/Utilizador/Find
	// Serviço nº2
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<UserEntity> getAllUsers() {
		return user.findAllUsers();
	}

	@GET
	@Path("/Count")
	// ACEDER ATRAVÉS DO PATH
	// http://localhost:8080/thews-ws/rest/Utilizador/Count
	// Serviço nº1
	 @Produces(MediaType.TEXT_PLAIN)
	public int getCountAllUsers() {
		return user.findAllUsers().size();
	}

	@GET
	@Path("/List/{username}")
	// ACEDER ATRAVÉS DO PATH
	// http://localhost:8080/thews-ws/rest/Utilizador/List/email
	// Serviço nº 3 Procurar por email
	@Produces(MediaType.APPLICATION_XML)
	public UserEntity getUserByEmail(@PathParam("username") String username) {
		return user.getUserEntity(username);
	}

	@POST
	@Path("/Add")
	// ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Utilizador/Add
	@Consumes({ MediaType.APPLICATION_XML })
	// Serviço nº 14 Adiciona Utilizador
	public Response createUser(UserEntity u) {
		user.registerNewUser(u);
		return Response.status(200).build();
	}

	@DELETE
	@Path("/Delete/{id}")
	// Aceder Através do path
	// http://localhost:8080/thews-ws/rest/Utilizador/Delete/id
	@Produces(MediaType.APPLICATION_XML)
	// Serviço nº 14 remover Utilizador
	public Response removeUserById(@PathParam("id") int id) {
		// UserEntity u = user.getUserFromId(id);
		boolean removed = user.deleteUser(user.getUserFromId(id));
		if (removed)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}

	@POST
	@Path("Change/{id}/{password}")
	// Aceder Através do path
	// http://localhost:8080/thews-ws/rest/Utilizador/Change/id/password
	// Serviço nº 15
	public void changePw(@PathParam("id") int id,
		@PathParam("password") String password) {
		UserEntity u = user.getUserFromId(id);
		PasswordEncryptor pe = new PasswordEncryptor();
		String ePassword = pe.encrypt(password);
		u.setPassword(ePassword);
		
	}

	@DELETE
	@Path("Musicdelete/{id}")
	// Aceder Através do path
	// http://localhost:8080/thews-ws/rest/Utilizador/Musicdelete/id
	@Produces(MediaType.APPLICATION_XML)
	// Serviço nº 16
	public Response deleteMusic(@PathParam("id") int id) {
		UserEntity u = user.getUserFromId(id);
		System.out.println("Utilizador --->  " + u.getName());
		boolean ok;
		ok = musics.removerUserOwnership(u);
		if (ok)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}

	@GET
	@Path("/FindLogged")
	// ACEDER ATRAVÉS DO PATH
	// http://localhost:8080/thews-ws/rest/Utilizador/FindLogged
	// Serviço nº5
	@Produces(MediaType.APPLICATION_XML)
	public List<UserEntity> getAllLogged() {
		return logged.getLoggedUsers();
	}

	@GET
	@Path("/CountLogged")
	// ACEDER ATRAVÉS DO PATH
	// http://localhost:8080/thews-ws/rest/Utilizador/CountLogged
	// Serviço nº4
	 @Produces(MediaType.TEXT_PLAIN)
	public String countAllLogged() {
		return (String.valueOf(logged.getLoggedUsers().size()));
	}

}
