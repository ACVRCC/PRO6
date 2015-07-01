package projecto4.grupo1.albertoricardo;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("/Playlist") //TUDO O QUE ESTÁ NESTA CLASSE FICA NO PATH /Playlist
public class SimplePlaylistService {

	
	
	@Inject
	private PlaylistEJBLocal playlist;
	
	@GET
	//Serviço nº7
	@Path("/Find") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Playlist/Find
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public List<PlaylistEntity> getAllPlaylist(){
		return  playlist.getPlaylists();
	}
	@GET
	@Path("/Count") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Playlist/Count
	//Serviço nº 6
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public int  getCountPlaylists(){
		return playlist.getPlaylists().size();
	}
	@GET
	@Path("/List/{id}") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Playlist/List/name
	//Serviço nº 9
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public List<PlaylistEntity> getUserByEmail(@PathParam ("id")int id){	
		return playlist.getOwnPlaylists(id);
	}
	@GET
	@Path("/ListMusic/{id}") //ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Playlist/List/name
	//Serviço nº 8 pedido por id da playlist
	@Produces(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
	public List<PlaylistEntity> getMusicFromPlaylists(@PathParam ("id")int id){	
		return playlist.getMusicFromPlaylists(id);
	}

}
