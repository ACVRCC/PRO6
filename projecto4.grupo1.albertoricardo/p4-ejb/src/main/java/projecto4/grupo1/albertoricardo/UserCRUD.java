package projecto4.grupo1.albertoricardo;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserCRUD {

	@PersistenceContext(name="Playlist")
	private EntityManager em;


	public UserEntity create(UserEntity user) {
		return em.merge(user);
	}
	
	public UserEntity update(UserEntity user) {
		return em.merge(user);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(UserEntity user) {
		em.remove(em.merge(user));

	}
	
	public UserEntity find(Object id) {
		return em.find(UserEntity.class, id);
	}


}