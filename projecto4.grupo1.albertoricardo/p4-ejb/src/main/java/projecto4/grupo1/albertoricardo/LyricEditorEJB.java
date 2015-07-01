package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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


	public void uploadLyricDB(int idUserOwner, int idMusic) {

		LyricEntity le = new LyricEntity();
		LyricsRest lr = new LyricsRest();
		MusicEntity me = em.find(MusicEntity.class, idMusic);
		UserEntity ue =em.find(UserEntity.class,idUserOwner);	

		le.setLyric(lr.getLyric(me.getTitle(), me.getArtist()));
		le.setMusic(me);
		le.setUserOwner(ue);

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
	
	public void wikiaRestLyricDB(int idUserOwner, int idMusic) {

		LyricEntity le = new LyricEntity();
		LyricsRest lr = new LyricsRest();
		MusicEntity me = em.find(MusicEntity.class, idMusic);
		UserEntity ue =em.find(UserEntity.class,idUserOwner);	

		le.setLyric(lr.getLyric(me.getTitle(), me.getArtist()));
		le.setMusic(me);
		le.setUserOwner(ue);

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
	
	public String readLyric(int idUserOwner, int idMusic){
		
			LyricEntity le = new LyricEntity();
			
				Query q = em.createQuery("SELECT le FROM LyricEntity le WHERE le.music.id = :mi AND le.userOwner.id= :ui")
						.setParameter("ui", idUserOwner)
						.setParameter("mi", idMusic);
				log.info("Consulta à base de dados para obter letra de música ");
			try {
				le = (LyricEntity) q.getSingleResult();
				
			} catch (Exception e) {
				e.printStackTrace();
				log.warn("Erro ao obter letra de música");
			}
			System.out.println(le.getLyric());
			return le.getLyric();
	}
		
		

	
}