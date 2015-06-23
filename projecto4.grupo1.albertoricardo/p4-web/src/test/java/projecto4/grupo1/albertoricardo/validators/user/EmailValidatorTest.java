package projecto4.grupo1.albertoricardo.validators.user;


import javax.faces.validator.ValidatorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.UserEJBLocal;
import projecto4.grupo1.albertoricardo.UserEntity;
import projecto4.grupo1.albertoricardo.validators.EmailValidator;

@RunWith(MockitoJUnitRunner.class)
public class EmailValidatorTest {
	
	@Mock
	UserEJBLocal userejb;
	@InjectMocks
	EmailValidator ev;

	@Test(expected=ValidatorException.class)
	public void wrongFormatEmail() {
		ev.validate(null, null, "justusername");
	}
	
	@Test(expected=ValidatorException.class)
	public void emailTaken() {
		String email = "some@email.com";
		Mockito.when(userejb.getUserEntity(email)).thenReturn(new UserEntity());
		ev.validate(null, null, email);
	}

}
