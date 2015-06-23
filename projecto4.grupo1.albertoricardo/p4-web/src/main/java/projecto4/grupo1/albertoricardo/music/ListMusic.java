package projecto4.grupo1.albertoricardo.music;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import projecto4.grupo1.albertoricardo.MusicEntity;
import projecto4.grupo1.albertoricardo.MusicListEJBLocal;
import projecto4.grupo1.albertoricardo.user.UserLogged;

@Named
@RequestScoped
public class ListMusic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MusicListEJBLocal mlejb;

	@Inject
	private UserLogged userlog;

	private List<MusicEntity> musics;

	private List<MusicEntity> ownMusics;

	private List<MusicEntity> filteredMusics;
	
	private MusicEntity selectedMusic;



	public List<MusicEntity> getMusics() {
		if (musics == null) {
			try {
				musics = mlejb.listMusics();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return musics;

	}

	public List<MusicEntity> getOwnMusic() {
		if (ownMusics == null ) {
			try {
				ownMusics = mlejb.listOwnMusics(userlog.getUser());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ownMusics;
	}

	public void removeProperty(MusicEntity m) {
		mlejb.removerMusicUserOwnership(m, userlog.getUser());
		refresh();
	}



	public void onRowEdit(RowEditEvent event) {
		mlejb.update((MusicEntity) event.getObject());

		FacesMessage msg = new FacesMessage("Música "+((MusicEntity) event.getObject()).getTitle()+" editado com successo.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private void refresh() {
		try {
			ownMusics = mlejb.listOwnMusics(userlog.getUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MusicEntity> getFilteredMusics() {
		return filteredMusics;
	}

	public void setFilteredMusics(List<MusicEntity> filteredMusics) {
		this.filteredMusics = filteredMusics;
	}

	public MusicEntity getSelectedMusic() {
		return selectedMusic;
	}

	public void setSelectedMusic(MusicEntity selectedMusic) {
		this.selectedMusic = selectedMusic;
	}

	public List<MusicEntity> getOwnMusics() {
		return ownMusics;
	}

	public void setOwnMusics(List<MusicEntity> ownMusics) {
		this.ownMusics = ownMusics;
	}

	public void setMusics(List<MusicEntity> musics) {
		this.musics = musics;
	}



}