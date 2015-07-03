package projecto4.grupo1.albertoricardo;


import java.util.ArrayList;

import javax.ejb.Local;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import projecto4.grupo1.albertoricardo.entities.UserEntity;

@XmlRootElement
@Local
public interface UserEJBLocal {

	public abstract boolean verifyLogin(String email, String password);

	public void registerUser(String username, String password, String name);

	int getUserID(String username);

	String getName(String username);
	
	UserEntity getUserEntity(String username);

	boolean changeUser(UserEntity user);

	boolean deleteUser(UserEntity user);

	
	public ArrayList<UserEntity> findAllUsers();

	public int countAllUsers();

	public UserEntity getUserFromId(int id);

	public void registerNewUser(UserEntity u);
}