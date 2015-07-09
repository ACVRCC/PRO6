package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

import projecto4.grupo1.albertoricardo.MusicListEJBLocal;
import projecto4.grupo1.albertoricardo.PlaylistFacade;
import projecto4.grupo1.albertoricardo.PlaylistEJBLocal;
import projecto4.grupo1.albertoricardo.entities.MusicEntity;
import projecto4.grupo1.albertoricardo.entities.PlaylistEntity;

@Stateless
@Path("/Playlist")
// TUDO O QUE ESTÁ NESTA CLASSE FICA NO PATH /Playlist
public class SimplePlaylistService {

	@EJB
	private PlaylistEJBLocal playlist;
	@EJB
	private MusicListEJBLocal musics;

	@GET
	// Serviço nº7
	@Path("/Find")
	// ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Playlist/Find
	@Produces(MediaType.APPLICATION_XML)
	public List<PlaylistEntity> getAllPlaylist() {
		return playlist.getPlaylists();
	}

	@GET
	@Path("/Count")
	// ACEDER ATRAVÉS DO PATH http://localhost:8080/thews-ws/rest/Playlist/Count
	// Serviço nº 6
	@Produces(MediaType.TEXT_PLAIN)
	public int getCountPlaylists() {
		return playlist.getPlaylists().size();
	}

	@GET
	@Path("/List/{id}")
	// ACEDER ATRAVÉS DO PATH
	// http://localhost:8080/thews-ws/rest/Playlist/List/id
	// Serviço nº 9
	@Produces(MediaType.APPLICATION_XML)
	public List<PlaylistEntity> getUserByEmail(@PathParam("id") int id) {
		return playlist.getOwnPlaylists(id);
	}

	@GET
	@Path("/ListMusic/{id}")
	// ACEDER ATRAVÉS DO PATH
	// http://localhost:8080/thews-ws/rest/Playlist/ListMusic/id
	// Serviço nº 8 pedido por id da playlist
	@Produces(MediaType.APPLICATION_XML)
	public List<MusicEntity> getMusicFromPlaylists(@PathParam("id") int id) {
		return playlist.getMusicFromPlaylists(id);
	}

	@GET
	@Path("/Addmusic/{idmusic}/{idplaylist}")
	// Aceder através do path:
	// http://localhost:8080/thews-ws/rest/Playlist/Addmusic
	// Serviço nº 17
	@Produces(MediaType.APPLICATION_XML)
	public void addMusicPlaylist(@PathParam("idmusic") int idmusic,
			@PathParam("idplaylist") int idplaylist) {
		MusicEntity m = musics.getMusicFromId(idmusic);

		PlaylistEntity p = playlist.getPlaylistFromId(idplaylist);

		playlist.updateMusic(p, m);

	}

	@GET
	@Path("/Deletemusic/{idmusic}/{idplaylist}")
	// Aceder através do path:
	// http://localhost:8080/thews-ws/rest/Playlist/Deletemusic/
	// Serviço nº 17
	@Produces(MediaType.APPLICATION_XML)
	public void DeleteMusicFromPlaylist(@PathParam("idmusic") int idmusic,
			@PathParam("idplaylist") int idplaylist) {
		MusicEntity m = musics.getMusicFromId(idmusic);

		PlaylistEntity p = playlist.getPlaylistFromId(idplaylist);

		playlist.removeMusic(p, m);

	}
}
