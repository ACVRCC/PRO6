package projecto4.grupo1.albertoricardo.user;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.UserEJBLocal;
import projecto4.grupo1.albertoricardo.validators.UserValidator;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {
	
	@Mock
	UserEJBLocal userejb;
	@Mock
	UIComponent component;
	@Mock
	FacesMessage msg;
	@Mock
	FacesContext context;
	
	
	@InjectMocks
	UserValidator uv;
	
	@Ignore
	@Test(expected=ValidatorException.class)
	public void test() {
		String email = "testMail@mail.com";
		Mockito.when(userejb.getName(email)).thenReturn(null);
		Mockito.verify(uv).validate(context, component, email);
		
	}

}
