package program;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MakeSha1 {
	
	// gera um hash SHA1 apartir do parametro recebido e retorna-o
	public static String GenerateSHA1(String text) {
		
		 String sha1 = "";
		 
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(text.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return sha1;
		
	}

}
