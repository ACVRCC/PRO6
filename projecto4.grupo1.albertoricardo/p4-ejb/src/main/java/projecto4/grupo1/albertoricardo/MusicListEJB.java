package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.MusicEntity;




@Stateless
public class MusicListEJB implements MusicListEJBLocal {

	@PersistenceContext(name="Playlist")
	private EntityManager em;

	@EJB
	private MusicEJBLocal crud;

	private static Logger log = LoggerFactory.getLogger(MusicListEJB.class);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MusicEntity> listMusics() {
		Query q = em.createQuery("select m from MusicEntity m");
		List<MusicEntity> lme = new ArrayList<MusicEntity>();
		lme = q.getResultList();
		log.info("Consulta À base de dados para obter lista de músicas");
		return lme;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MusicEntity> listOwnMusics(UserEntity user) {
		List<MusicEntity> me = new ArrayList<>();
		Query q = em.createQuery("SELECT m FROM MusicEntity m where m.userOwner.id = :u")
				.setParameter("u", user.getId());
		log.info("Consulta À base de dados para obter lista de músicas do utilizador "+user.getName());
		try {
			me = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Erro ao obter lista de músicas do utilizador "+user.getName());
		}
		return me;
	}
	
	@Override
	public boolean update(MusicEntity music) {
		boolean success = false;
		try {
			crud.update(music);
			success = true;
			log.info("Alteração feita à música com o ID: "+music.getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Erro na tentativa de alteração à música com o ID: "+music.getId());
		}
		
		return success;
	}
	
	@Override
	public boolean removerUserOwnership(UserEntity user) {
		boolean success = false;
		try {
			int complete = em.createQuery("UPDATE MusicEntity m SET m.userOwner.id = NULL where m.userOwner.id = :i")
					.setParameter("i", user.getId())
					.executeUpdate();
			if (complete > 0) {
				log.info("Alteração de propriedade a música");
				success = true;
				FacesMessage msg = new FacesMessage("Música","Propriedade removida com sucesso.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			log.error("Erro ao remover proprietário da música");
			FacesMessage msg = new FacesMessage("Erro",e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return success;
		
	}

	@Override
	public void removerMusicUserOwnership(MusicEntity m, UserEntity user) {
		try {
			int complete = em.createQuery("UPDATE MusicEntity m SET m.userOwner.id = NULL where m.userOwner.id = :i AND m.id = :mid")
					.setParameter("i", user.getId())
					.setParameter("mid", m.getId())
					.executeUpdate();
			if (complete > 0) { 
				FacesMessage msg = new FacesMessage("Música","Propriedade removida com sucesso.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			log.error("Erro ao remover proprietário da música");
			FacesMessage msg = new FacesMessage("Erro",e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
}