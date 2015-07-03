package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projecto4.grupo1.albertoricardo.entities.MusicEntity;




@Stateless
public class MusicEJB implements MusicEJBLocal {
	
    @PersistenceContext(name="Playlist")
    private EntityManager em;

  
	@Override
	public MusicEntity create(MusicEntity music) {
		return em.merge(music);
	}
	@Override
	public MusicEntity update(MusicEntity music) {
		return em.merge(music);
	}
	@Override
	public void remove(MusicEntity music) {
		 em.remove(em.merge(music));
		
	}
	@Override
	public MusicEntity find(Object id) {
		  return em.find(projecto4.grupo1.albertoricardo.entities.MusicEntity.class, id);
	}
}