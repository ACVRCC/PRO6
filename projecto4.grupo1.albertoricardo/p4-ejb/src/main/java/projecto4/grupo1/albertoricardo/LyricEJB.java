package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projecto4.grupo1.albertoricardo.entities.LyricEntity;

@Stateless
public class LyricEJB implements LyricEJBLocal{

	  @PersistenceContext(name="Playlist")
	    private EntityManager em;

	
	  
		@Override
		public LyricEntity create(LyricEntity lyric) {
			return em.merge(lyric);
		}
		@Override
		public LyricEntity update(LyricEntity lyric) {
			return em.merge(lyric);
		}
		@Override
		public void remove(LyricEntity lyric) {
			 em.remove(em.merge(lyric));
			
		}
		@Override
		public LyricEntity find(Object id) {
			  return em.find(LyricEntity.class, id);
		}
	
	
	
	
	
	

}
