package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import projecto4.grupo1.albertoricardo.entities.UserEntity;

@LocalBean
@Singleton
public class LoggedUsers {

	public LoggedUsers() {

	}

	private List<UserEntity> loggedUsers = new ArrayList<UserEntity>();

	public void addLogged(UserEntity u) {
		System.out.println("adiciona a lista de logados******************");
		System.out.println(u.getId() + "\t" + u.getEmail() + "\t"
				+ u.getPassword());
		loggedUsers.add(u);
		System.out.println("USER LOGADO ==> " + loggedUsers);
	}

	public void removeLogged(UserEntity u) {
		loggedUsers.remove(u);
	}

	public int countLogged() {
		return loggedUsers.size();
	}

	public List<UserEntity> getLoggedUsers() {
		return loggedUsers;
	}

}
