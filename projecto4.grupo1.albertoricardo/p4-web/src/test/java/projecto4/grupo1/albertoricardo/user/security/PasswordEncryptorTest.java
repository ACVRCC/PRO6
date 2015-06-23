package projecto4.grupo1.albertoricardo.user.security;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import projecto4.grupo1.albertoricardo.security.PasswordEncryptor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class PasswordEncryptorTest {
	
	@InjectMocks PasswordEncryptor pe;

	@Test
	public void PasswordEncryptionTest() {
		String password = "abc";
		String pwEncrypted = pe.encrypt(password);
		assertThat(pwEncrypted, not(password));
	}
	
	@Test
	public void PasswordMatcherTest() {
		String password = "thisPa55word";
		String encPassword = pe.encrypt(password);
		boolean testRslt = pe.check(password, encPassword);
		assertTrue(testRslt);
	}
	
	@Test
	public void PasswordMatcherFailTest() {
		String password = "thisPa55word";
		String encPassword = pe.encrypt("an0therPa55word");
		boolean testRslt = pe.check(password, encPassword);
		assertFalse(testRslt);
	}

}
