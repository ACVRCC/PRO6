package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.entities.PlaylistEntity;

@Stateless
public class PlaylistFacade implements IPlaylistFacade {

	private static Logger log = LoggerFactory.getLogger(PlaylistFacade.class);

	@PersistenceContext(name = "Playlist")
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * projecto4.grupo1.albertoricardo.IPlaylistFacade#create(projecto4.grupo1
	 * .albertoricardo.entities.PlaylistEntity)
	 */
	@Override
	public PlaylistEntity create(PlaylistEntity playlist) {
		return em.merge(playlist);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * projecto4.grupo1.albertoricardo.IPlaylistFacade#update(projecto4.grupo1
	 * .albertoricardo.entities.PlaylistEntity)
	 */
	@Override
	public PlaylistEntity update(PlaylistEntity playlist) {
		log.debug("Update à playlist " + playlist.getName());
		return em.merge(playlist);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * projecto4.grupo1.albertoricardo.IPlaylistFacade#remove(projecto4.grupo1
	 * .albertoricardo.entities.PlaylistEntity)
	 */
	@Override
	public void remove(PlaylistEntity playlist) {
		em.remove(em.merge(playlist));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * projecto4.grupo1.albertoricardo.IPlaylistFacade#find(java.lang.Object)
	 */
	@Override
	public PlaylistEntity find(Object id) {
		return em.find(PlaylistEntity.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see projecto4.grupo1.albertoricardo.IPlaylistFacade#findAll()
	 */
	@Override
	public List<PlaylistEntity> findAll() {
		TypedQuery<PlaylistEntity> q = em.createQuery(
				"SELECT p FROM PlaylistEntity p", PlaylistEntity.class);

		List<PlaylistEntity> playlistEntities = q.getResultList();

		return playlistEntities;
	}

	@Override
	public List<PlaylistEntity> findAllByUserId(int userId) {
		TypedQuery<PlaylistEntity> q = em.createQuery(
				"SELECT p FROM PlaylistEntity p WHERE p.userOwner.id = :id",
				PlaylistEntity.class).setParameter("id", userId);

		List<PlaylistEntity> playlistEntities = q.getResultList();

		return playlistEntities;
	}
}
