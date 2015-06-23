package projecto4.grupo1.albertoricardo;


import javax.ejb.Local;

@Local
public interface UserEJBLocal {

	public abstract boolean verifyLogin(String email, String password);

	public abstract void registerUser(String username, String password, String name);

	int getUserID(String username);

	String getName(String username);
	
	UserEntity getUserEntity(String username);

	boolean changeUser(UserEntity user);

	boolean deleteUser(UserEntity user);
	
	

}