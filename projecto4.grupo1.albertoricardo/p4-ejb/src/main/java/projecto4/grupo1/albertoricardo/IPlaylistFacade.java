package projecto4.grupo1.albertoricardo;

import java.util.List;

import javax.ejb.Local;

import projecto4.grupo1.albertoricardo.entities.PlaylistEntity;

@Local
public interface IPlaylistFacade {

	PlaylistEntity create(PlaylistEntity playlist);

	PlaylistEntity update(PlaylistEntity playlist);

	void remove(PlaylistEntity playlist);

	PlaylistEntity find(Object id);

	List<PlaylistEntity> findAll();

	List<PlaylistEntity> findAllByUserId(int userId);
}