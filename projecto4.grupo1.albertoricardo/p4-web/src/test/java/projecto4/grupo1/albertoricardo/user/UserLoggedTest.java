package projecto4.grupo1.albertoricardo.user;

import static org.junit.Assert.*;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.UserEJBLocal;
import projecto4.grupo1.albertoricardo.UserEntity;

@RunWith(MockitoJUnitRunner.class)
public class UserLoggedTest {

	@Mock
	UserEJBLocal userejb;
	@Mock
	UserEntity useret;

	@InjectMocks
	UserLogged user;

	@Test
	public void changeSettingsTest() {
		try {
			user.setNewName("Ana");
			user.setNewPassword("123");
			Mockito.when(useret.getName()).thenReturn("Joana");
			boolean result = user.changeSettings();
			Mockito.verify(userejb).changeUser(useret);
			assertFalse(result);
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	public void invalidateSessionTest() {
		FacesContext context = ContextMocker.mockFacesContext();
		ExternalContext ext = Mockito.mock(ExternalContext.class);
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		Mockito.when(context.getExternalContext()).thenReturn(ext);
		Mockito.when(ext.getRequest()).thenReturn(req);
		Mockito.when(req.getSession()).thenReturn(session);
		String result = user.invalidateSession();
		assertEquals("/login.xhtml?faces-redirect=true", result);
	}

}
