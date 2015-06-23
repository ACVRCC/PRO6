package projecto4.grupo1.albertoricardo.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
	
	
	
	public PasswordEncryptor() {
	}

	public String encrypt(String password) {
		String securedPassword = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			
			byte byteData[] = md.digest();
			
			StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1)); // Converter para uma String HEX
	        }
	        
	        securedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return securedPassword;
	}
	
	public boolean check(String passwordUnsecured, String passwordEncrypted) {
		boolean pwMatch = false;
		
		String pw1 = encrypt(passwordUnsecured);
		
		if (pw1.equals(passwordEncrypted)) pwMatch = true;
		else pwMatch = false;
		
		return pwMatch;
	}

}
