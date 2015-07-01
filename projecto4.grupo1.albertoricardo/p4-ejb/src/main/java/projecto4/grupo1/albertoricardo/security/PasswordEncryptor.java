package projecto4.grupo1.albertoricardo.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.sun.syndication.io.impl.Base64;
public class PasswordEncryptor {
	
	
	
	public PasswordEncryptor() {
	}
//	public String encrypt(String password) {
//	String securedPassword = "";
//	
//	try {
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update(password.getBytes());
//		
//		byte byteData[] = md.digest();
//		
//		StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < byteData.length; i++) {
//         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1)); // Converter para uma String HEX
//        }
//        
//        securedPassword = sb.toString();
//	} catch (NoSuchAlgorithmException e) {
//		e.printStackTrace();
//	}
//	
//	return securedPassword;
//}

public String encrypt(String password){
	String generatedPassword = null;
	try {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();
        byte[] data2 = Base64.encode(byteData);
        generatedPassword = new String(data2);
        return generatedPassword;
	}
	catch (NoSuchAlgorithmException e){
		System.out.println(e.getMessage());
		return null;
	}
}

public boolean check(String passwordUnsecured, String passwordEncrypted) {
	boolean pwMatch = false;
	
	String pw1 = encrypt(passwordUnsecured);
	
	if (pw1.equals(passwordEncrypted)) pwMatch = true;
	else pwMatch = false;
	
	return pwMatch;
}

}
