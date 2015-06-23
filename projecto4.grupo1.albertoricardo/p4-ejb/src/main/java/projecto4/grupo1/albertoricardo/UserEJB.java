package projecto4.grupo1.albertoricardo;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.security.PasswordEncryptor;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
public class UserEJB implements UserEJBLocal {
	@PersistenceContext(name="Playlist")
	EntityManager em;

	@EJB
	private UserCRUD crud;
	@EJB
	private MusicListEJBLocal mlistejb;
	
	private static Logger log = LoggerFactory.getLogger(UserEJB.class);
	
	public UserEJB() {

	}

	@Override
	public boolean verifyLogin(String email, String password) {
		boolean verified;
		Query q = em.createQuery("select u from UserEntity u where u.email like :e");
		q.setParameter("e", email);
		try {
			UserEntity usr = (UserEntity) q.getSingleResult();
			PasswordEncryptor pe = new PasswordEncryptor();
			if (pe.check(password, usr.getPassword())) verified = true;
			else verified = false;
		} catch (NoResultException nre) {
			verified = false;
			nre.printStackTrace();
			log.warn("catch exception : verifyLogin method", nre);
		}

		return verified;
	}

	@Override
	public void registerUser(String username, String password, String name) {
		PasswordEncryptor pe = new PasswordEncryptor();
		String ePassword = pe.encrypt(password);
		UserEntity u = new UserEntity(username, ePassword, name);
		crud.create(u);
		log.info("Novo utilizador registado: "+username);
	}

	@Override
	public boolean changeUser(UserEntity user) {
		boolean success = false;
		try {
			crud.update(user);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;

	}

	@Override
	public boolean deleteUser(UserEntity user) {
		boolean success = false;
		try {
			mlistejb.removerUserOwnership(user);
			crud.remove(user);
			success = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public int getUserID(String username) {
		Query q = em.createQuery("select u.id from UserEntity u where u.email like :e");
		q.setParameter("e", username);
		int id = (Integer) q.getSingleResult();
		return id;
	}

	@Override
	public String getName(String username) {
		String name = null;
		try {
			Query q = em.createQuery("select u from UserEntity u where u.email like :e");
			q.setParameter("e", username);
			UserEntity u = (UserEntity) q.getSingleResult();
			name = u.getName();
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage("Login", 
					"Utilizador inexistente.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		return name;
	}

	@Override
	public UserEntity getUserEntity(String username) {
		UserEntity u = null;
		try {
			Query q = em.createQuery("select u from UserEntity u where u.email like :e");
			q.setParameter("e", username);
			u = (UserEntity) q.getSingleResult();
		} catch(NoResultException nre) {
			nre.printStackTrace();
		}
		return u;

	}

}
