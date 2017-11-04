package fox.hound.spring.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

	/**
	 * Encriptamos una cadena usando MD5
	 * 
	 *	@param c: cadena a encriptar
	 *	@return cadena encriptada 
	 **/
	public String md5(String c) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			 byte[] md5sum = md.digest(c.getBytes());
			 return String.format("%032X", new BigInteger(1, md5sum));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String encodeBase64(String c) {
		return Base64.getEncoder().encodeToString(c.getBytes());
	}
	
	public String decodeBase64(String c) {
		byte [] decode = Base64.getDecoder().decode(c); 
		return new String(decode);
	}
	
}
