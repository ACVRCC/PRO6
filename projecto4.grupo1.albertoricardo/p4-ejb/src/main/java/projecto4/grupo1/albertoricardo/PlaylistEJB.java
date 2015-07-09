package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.dto.DozerHelper;
import projecto4.grupo1.albertoricardo.dto.PListDTO;
import projecto4.grupo1.albertoricardo.entities.MusicEntity;
import projecto4.grupo1.albertoricardo.entities.PlaylistEntity;
import projecto4.grupo1.albertoricardo.entities.UserEntity;

@Stateless
public class PlaylistEJB implements PlaylistEJBLocal {

	@PersistenceContext(name = "Playlist")
	private EntityManager em;

	@EJB
	private IPlaylistFacade pl_crud;
	@EJB
	private MusicListEJBLocal musics;

	@EJB
	private IPlaylistFacade playlistFacade;

	private static Logger log = LoggerFactory.getLogger(PlaylistEJB.class);

	@Override
	public void addPlaylist(String name, Date insertDate, UserEntity userlogged) {
		PlaylistEntity pl = new PlaylistEntity();
		pl.setName(name);
		pl.setInsertDate(insertDate);
		pl.setUserOwner(userlogged);
		em.persist(pl);
	}

	@Override
	public List<PListDTO> getPlaylistDozer() {
		List<PListDTO> pldto = new ArrayList<>();
		try {
			List<PlaylistEntity> pe = playlistFacade.findAll();

			Mapper mapper = new DozerBeanMapper();

			pldto = DozerHelper.map(mapper, pe, PListDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pldto;
	}

	@Override
	public List<PlaylistEntity> getPlaylists() {
		List<PlaylistEntity> pe = new ArrayList<>();
		try {
			pe = playlistFacade.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("catch exception - getPlaylists method");
		}
		return pe;
	}

	@Override
	public List<PlaylistEntity> getOwnPlaylists(int id) {
		List<PlaylistEntity> pe = new ArrayList<>();

		try {
			pe = playlistFacade.findAllByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("catch exception - getOwnPlaylists method");
		}

		return pe;
	}

	@Override
	public boolean findName(String name) {
		boolean found = false;
		try {
			Query q = em
					.createQuery("select u from PlaylistEntity u where u.name like :e");
			q.setParameter("e", name);
			if (q.getSingleResult() != null)
				found = true;
		} catch (NoResultException nre) {
			found = false;
			log.warn("catch exception - findName method");
		}
		return found;
	}

	@Override
	public boolean removePlaylistsOfUser(UserEntity u) {
		boolean success = false;
		try {
			Query q = em
					.createQuery("DELETE FROM PlaylistEntity p where p.userOwner.id = :i");
			q.setParameter("i", u.getId());
			int complete = q.executeUpdate();
			if (complete > 0)
				success = true;
		} catch (Exception e) {

			log.warn("catch exception - removePlaylistsOfUser method");
		}

		return success;

	}

	@Override
	public boolean removePlaylistFromUser(PlaylistEntity p) {
		boolean success = false;
		try {
			p.getMusics().clear();
			pl_crud.remove(p);
			success = true;

		} catch (Exception e) {
			log.error("Erro ao tentar remover playlist", e);

		}

		return success;
	}

	@Override
	public void updateName(int id, String name) {

		pl_crud.find(id).setName(name);

	}

	@Override
	public void update(PlaylistEntity playlist) {
		pl_crud.update(playlist);
	}

	// ***********************************************
	public List<MusicEntity> getMusicFromPlaylists(int id) {
		List<MusicEntity> pe = new ArrayList<>();
		try {
			Query q = em
					.createQuery("SELECT m from PlaylistEntity p JOIN p.musics m Where p.id= :id");
			q.setParameter("id", id);

			pe = (ArrayList<MusicEntity>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("catch exception - getPlaylists method");
		}
		return pe;
	}

	public PlaylistEntity getPlaylistFromId(int id) {
		PlaylistEntity p = null;
		try {
			Query q = em
					.createQuery("SELECT p from PlaylistEntity p WHERE p.id = :id");
			q.setParameter("id", id);
			p = (PlaylistEntity) q.getSingleResult();
		} catch (NoResultException nre) {
			nre.printStackTrace();
		}
		return p;
	}

	@Override
	public void updateMusic(PlaylistEntity playlist, MusicEntity musics) {

		playlist.getMusics().add(musics);
		pl_crud.update(playlist);
	}

	@Override
	public void removeMusic(PlaylistEntity playlist, MusicEntity musics) {

		playlist.getMusics().remove(musics);
		pl_crud.update(playlist);
	}

}