package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rest.LyricsRest;

@Stateless
public class LyricEditorEJB {


	@PersistenceContext(name="Playlist")
	private EntityManager em;

	private static Logger log = LoggerFactory.getLogger(LyricEditorEJB.class);

	@Inject
	private LyricEJBLocal crud;


	public void uploadLyricDB(int idMusic) {

		LyricEntity le = new LyricEntity();
		LyricsRest lr = new LyricsRest();
		MusicEntity me = em.find(MusicEntity.class, idMusic);

		le.setLyric(lr.getLyric(me.getTitle(), me.getArtist()));
		le.setMusic(me);
		le.setUserOwner(null);

		try {
			em.persist(le);
			FacesMessage msg = new FacesMessage("Letra","Upload realizado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			log.error("Erro a guardar nova letra",e);
			FacesMessage msg = new FacesMessage("Letra","Erro ao fazer upload.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void newUploadLyricDB(int idUserOwner, int idMusic) {

		LyricsRest lr = new LyricsRest();
		LyricEntity le = getLyricEntity(idUserOwner, idMusic);
		MusicEntity me = em.find(MusicEntity.class, idMusic);
		le.setLyric(lr.chartRestLyric(me.getTitle(), me.getArtist()));

	
		try {
			crud.update(le);
			FacesMessage msg = new FacesMessage("Letra","Upload realizado com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			log.error("Erro a guardar nova letra",e);
			FacesMessage msg = new FacesMessage("Letra","Erro ao fazer upload.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


	public void upSaveLyricDB(int idUserOwner, int idMusic, String editedLyric) {

		LyricEntity le = new LyricEntity();

		if(getLyricEntity(idUserOwner,idMusic).getLyric()==null){

			MusicEntity me = em.find(MusicEntity.class, idMusic);
			UserEntity ue =em.find(UserEntity.class,idUserOwner);	


			le.setLyric(editedLyric);
			le.setMusic(me);
			le.setUserOwner(ue);

			try {
				crud.update(le);
				FacesMessage msg = new FacesMessage("Letra","Gravação realizada com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.error("Erro a guardar nova letra",e);
				FacesMessage msg = new FacesMessage("Letra","Erro ao gravar letra.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else {		
			FacesMessage msg = new FacesMessage("Letra","Letra já existente. Consulte e edite em Playlists.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public boolean updateLyricDB(int idUserOwner, int idMusic, String editedLyric) {
		boolean success = false;

		LyricEntity le = getLyricEntity(idUserOwner, idMusic);
		le.setLyric(editedLyric);		
		try {
			crud.update(le);
			success = true;
			log.info("Alteração a letra com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Erro na tentativa de alteração a letra");
		}

		return success;
	}

	public String readOriginalLyric(int idMusic){

		LyricEntity le = new LyricEntity();
		log.info("Consulta à base de dados para obter letra de música original");
		Query q = em.createQuery("SELECT le FROM LyricEntity le WHERE le.music.id = :mi AND le.userOwner.id is NULL")
				.setParameter("mi", idMusic);			
		try {
			le = (LyricEntity) q.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Erro ao obter letra de música original");
		}
		return le.getLyric();
	}

	public LyricEntity getLyricEntity(int idUserOwner, int idMusic){

		LyricEntity le = new LyricEntity();
		log.info("Consulta à base de dados para obter letra de música editada");
		Query q = em.createQuery("SELECT le FROM LyricEntity le WHERE le.music.id = :mi AND le.userOwner.id= :ui")
				.setParameter("ui", idUserOwner)
				.setParameter("mi", idMusic);

		try {
			le = (LyricEntity) q.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao obter letra de música editada");
		}
		return le;
	}	


	public String readEditedLyric(int idUserOwner, int idMusic){
		return getLyricEntity(idUserOwner, idMusic).getLyric();
	}	


}