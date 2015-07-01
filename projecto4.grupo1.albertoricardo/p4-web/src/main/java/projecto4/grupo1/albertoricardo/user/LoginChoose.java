package projecto4.grupo1.albertoricardo.user;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class LoginChoose implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean showLogin = true;
	private boolean showRegister = false;

	public void doShowLogin() {
		showLogin = true;
		showRegister = false;
	}

	public void doShowRegister() {
		showLogin = false;
		showRegister = true;
	}
	
	public void toggle() {
		if (showLogin) {
			showLogin = false;
			showRegister = true;
		} else {
			showLogin = true;
			showRegister = false;
		}
	}

	public boolean isShowLogin() {
		return showLogin;
	}

	public void setShowLogin(boolean showL) {
		showLogin = showL;
	}

	public boolean isShowRegister() {
		return showRegister;
	}

	public void setShowRegister(boolean showR) {
		showRegister = showR;
	}
	
	



}
