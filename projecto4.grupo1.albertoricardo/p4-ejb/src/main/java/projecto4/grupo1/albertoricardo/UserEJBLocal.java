package projecto4.grupo1.albertoricardo;


import java.util.ArrayList;

import javax.ejb.Local;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
	@XmlTransient
	
	public ArrayList<UserEntity> findAllUsers();
	@XmlTransient
	public int countAllUsers();
	@XmlTransient
	public UserEntity getUserFromId(int id);
	@XmlTransient
	public void registerNewUser(UserEntity u);
}