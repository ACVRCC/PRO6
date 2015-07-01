package projecto4.grupo1.albertoricardo;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.xml.bind.annotation.XmlTransient;

import projecto4.grupo1.albertoricardo.dto.PListDTO;

@Local
public interface PlaylistEJBLocal {

	void addPlaylist(String name, Date insertDate, UserEntity userlogged);

	List<PListDTO> getPlaylistDozer();
	@XmlTransient
	List<PlaylistEntity> getPlaylists();

	List<PlaylistEntity> getOwnPlaylists(int id);

	boolean findName(String name);

	boolean removePlaylistsOfUser(UserEntity u);

	boolean removePlaylistFromUser(PlaylistEntity p);

	void updateName(int id, String name);

	void update(PlaylistEntity playlist);
	@XmlTransient
	public List<PlaylistEntity> getMusicFromPlaylists(int id);
}
