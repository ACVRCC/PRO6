package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class PlaylistCRUD {

	@PersistenceContext(name="Playlist")
	private EntityManager em;
	
	private static Logger log = LoggerFactory.getLogger(PlaylistCRUD.class);
	
	
	public PlaylistEntity create(PlaylistEntity playlist) {

		return em.merge(playlist);
	}

	
	public PlaylistEntity update(PlaylistEntity playlist) {
		log.debug("Update à playlist "+playlist.getName());
		return em.merge(playlist);
	}

	public void remove(PlaylistEntity playlist) {

		em.remove(em.merge(playlist));
	}


	public PlaylistEntity find(Object id) {

		return em.find(PlaylistEntity.class,id);
	}

	
}
