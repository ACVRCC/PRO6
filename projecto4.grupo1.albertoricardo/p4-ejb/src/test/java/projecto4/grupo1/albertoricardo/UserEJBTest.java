package projecto4.grupo1.albertoricardo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.UserEJB;
import projecto4.grupo1.albertoricardo.entities.UserEntity;
import projecto4.grupo1.albertoricardo.security.PasswordEncryptor;



@RunWith(MockitoJUnitRunner.class)
public class UserEJBTest {


	@Mock
	EntityManager em;

	@Mock
	Query mockedQuery;

	@InjectMocks
	UserEJB ejb;



	@Test
	public void verifyLoginTest(){

		final String q ="select u from UserEntity u where u.email like :e";
		PasswordEncryptor pe = new PasswordEncryptor();			
		when(mockedQuery.getSingleResult()).thenReturn(new UserEntity("usertest@mail.com",pe.encrypt("X"), "usertest"));
		when(em.createQuery(q)).thenReturn(mockedQuery);
		
		boolean result = ejb.verifyLogin("usertest@mail.com", "X");
		
		verify(mockedQuery).setParameter("e", "usertest@mail.com");
		verify(mockedQuery).getSingleResult();
        verify(em).createQuery(q);

        Assert.assertEquals(result,true);


	}






}


