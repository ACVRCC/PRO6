package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Stateless
@Path("/Music") //TUDO O QUE ESTÁ NESTA CLASSE FICA NO PATH /Music
public class SimpleMusicService {

	@Inject
	private MusicListEJBLocal music;
	@Inject
	private UserEJBLocal user;
	
	@GET
	@Path("/Find") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Music/Find
	//Serviço nº11
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public List<MusicEntity> listaMusics(){
		return  music.listMusics();
	}
	@GET
	@Path("/Count") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Music/Count
	//Serviço nº 10
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public int  getCountPlaylists(){
		return music.listMusics().size();
	}
	
	@GET
	@Path("/List/{id}") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Music/List/id
	//Serviço nº13 procura por id do utilizador
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<MusicEntity> getOwnMusics (@PathParam("id") int id){
	return music.getOwnMusics(id);
}
	@GET
	@Path("/ListMusic/{id}") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Music/ListMusic/name
	//Serviço nº12 procura por id da musica
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<MusicEntity> getMusicsFromId (@PathParam("id") int id){
	return music.getMusicsFromId(id);
}	
}


