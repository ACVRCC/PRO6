package projecto4.grupo1.albertoricardo.user;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.UserEJBLocal;

@Named
@RequestScoped
public class UserRegister implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(UserRegister.class);
	
	@Inject
	private UserEJBLocal userejb;
	
	@Inject
	private LoginChoose lc;

	private String email;
	private String password;
	private String passwordConfirm;
	private String name;
	private String result = "";
	

	public UserRegister() {
		super();
	}



	public String addNewUser() {
		userejb.registerUser(email, password, name);
		log.info("Novo utilizador registado: "+name+" ("+email+")");
		lc.toggle();
		return "login.xhtml?faces-redirect=true";
	}



	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPasswordConfirm() {
		return passwordConfirm;
	}



	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}





}
