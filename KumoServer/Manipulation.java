package KumoServer;


import java.security.MessageDigest;
import java.util.Base64;

public class Manipulation {
	public boolean validate(String str, boolean change){
		if (change){
			return str!=null && str.matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*") && str.matches(".*[0-9].*") && str.matches(".*[A-Z].*") && str.matches(".*[a-z].*") && !str.isEmpty() && str.split(" ").length == 1 && str.length() >= 8;
		}
		else{
			return str!=null && !str.isEmpty() && str.split(" ").length == 1;
		}
	}
	public String hash(String password) {
		String hashedValue="";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte [] hash= md.digest(password.getBytes());
			hashedValue= Base64.getEncoder().encodeToString(hash);
			return hashedValue;
		} catch (Exception e) {
			System.out.println(e);
		}
		return hashedValue;
	}
}
