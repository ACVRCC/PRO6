package projecto4.grupo1.albertoricardo.playlist;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.PlaylistEJBLocal;
import projecto4.grupo1.albertoricardo.PlaylistEntity;
import projecto4.grupo1.albertoricardo.UserEntity;
import projecto4.grupo1.albertoricardo.user.UserLogged;

@Named
@ViewScoped
public class ListPlaylist implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ListPlaylist.class);
	@EJB
	private PlaylistEJBLocal plistejb;

	@Inject
	private UserLogged userlog;

	private List<PlaylistEntity> playlists;

	private PlaylistEntity selectedPlaylist;

	private MusicEntity selectedMusic;

	public List<PlaylistEntity> ownPlaylists(UserEntity u) {
		if (playlists == null) {
			try {
				playlists = plistejb.getOwnPlaylists(u.getId());
			} catch (Exception e) {
				e.printStackTrace();
				log.warn("catch exception - ownPlaylists method");
			}
		}

		return playlists;
	}

	public List<PlaylistEntity> getPlaylists() {
		if (playlists == null) {
			try {
				playlists = plistejb.getOwnPlaylists(userlog.getUser().getId());
			} catch (Exception e) {
				e.printStackTrace();
				log.warn("catch exception - getPlaylists method");
			}
		}
		return playlists;
	}

	public void addToSelectedPlaylist() {
		boolean duplicate = false;
		if (selectedPlaylist != null) {
			for (MusicEntity m:selectedPlaylist.getMusics()) {
				if (selectedMusic.getId() == m.getId()) { 
					duplicate = true;
					FacesMessage msg = new FacesMessage("Erro","Música " + selectedMusic.getTitle() + " já existe na Playlist "+selectedPlaylist.getName());
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
			if (!duplicate) {
				try {
					selectedPlaylist.getMusics().add(selectedMusic);
					plistejb.update(selectedPlaylist);
					FacesMessage msg = new FacesMessage("Playlists","Música "+selectedMusic.getTitle()+" adicionada com successo à playlist "+selectedPlaylist.getName());
					FacesContext.getCurrentInstance().addMessage(null, msg);
					refresh();
				} catch (Exception e) {
					FacesMessage msg = new FacesMessage("Erro",e.getMessage());
					FacesContext.getCurrentInstance().addMessage(null, msg);
					log.warn("catch exception - addToSelectedPlaylist method");
				}
			}
		}
	}

	public void removeMusicFromPlaylist(MusicEntity music) {
		PlaylistEntity playlist = selectedPlaylist;
		Iterator<MusicEntity> it = playlist.getMusics().iterator();
		while(it.hasNext()) {
			MusicEntity m = it.next();
			if (m.getId() == music.getId()) it.remove();
		}
		log.debug("Delete from playlist");
		plistejb.update(playlist);
		refresh();
		FacesMessage msg = new FacesMessage("Playlist","Música "+music.getTitle()+" removida com sucesso da playlist "+playlist.getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		log.warn("Música "+music.getTitle()+" removida da playlist "+playlist.getName()+" do utilizador "+playlist.getUserOwner().getName());
	}

	public void deletePlaylist(PlaylistEntity p) {
		plistejb.removePlaylistFromUser(p);
		refresh();
		playlists = getPlaylists();
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			plistejb.update((PlaylistEntity) event.getObject());
			FacesMessage msg = new FacesMessage("Playlist","Playlist "+((PlaylistEntity) event.getObject()).getName()+" editado com successo.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Playlist","Erro ao editar playlist");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.warn("catch exception - onRowEdit method");
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void refresh() {
		try {
			playlists = plistejb.getOwnPlaylists(userlog.getUser().getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("catch exception - refresh method");
		}
	}

	public void setPlaylists(List<PlaylistEntity> playlists) {
		this.playlists = playlists;
	}

	public PlaylistEntity getSelectedPlaylist() {
		return selectedPlaylist;
	}

	public void setSelectedPlaylist(PlaylistEntity selectedPlaylist) {
		this.selectedPlaylist = selectedPlaylist;
	}

	public MusicEntity getSelectedMusic() {
		return selectedMusic;
	}

	public void setSelectedMusic(MusicEntity selectedMusic) {
		this.selectedMusic = selectedMusic;
	}



}
