package projecto4.grupo1.albertoricardo.user;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projecto4.grupo1.albertoricardo.UserEJBLocal;

@Named
@SessionScoped
public class UserLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(UserLogin.class);

	@EJB
	private UserEJBLocal userejb;

	@SuppressWarnings("unused")
	@Inject
	private LoginChoose lc;

	@Inject
	private UserLogged userlog;

	private int id;
	private String email;
	private String password;
	private String result = "";

	public String doLogin() {
		System.out.println("passa 0");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		try{
			System.out.println("passa 1");
			System.out.println("PASSWORD :--> "+password);
			System.out.println("Email :--> "+email);
			request.login(email, password);
			
			System.out.println("passa 2");
			userlog.setUser(userejb.getUserEntity(email));
			log.info("Utilizador "+email+" iniciou sessão.");
			return "/Authorized/entry.xhtml?faces-redirect=true";
		} catch (ServletException e){ 
			System.out.println("erro 1");
			log.debug("Alguém está a tentar aceder a conta com " + email + " e " + password);
			return "/NonAuthorized.xhtml";
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
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





}
