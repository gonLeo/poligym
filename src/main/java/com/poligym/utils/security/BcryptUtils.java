package com.poligym.utils.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtils {
    private BcryptUtils() {}
	
	/**
	 * Method that encode the raw password.
	 */
	public static String getHash(String password) {
		
		if(password == null) {
			return null;
		}
		
		if(BcryptUtils.isEncrypted(password)) {
			return password;
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	/**
	 * Method that check if the password is already encoding.
	 */
	public static boolean isEncrypted(String password) {
		return password.startsWith("$2a$");
	}
	
	/**
	 * Method that decode the raw password.
	 */
	public static String decode(String password, String encrypted) throws Exception {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		boolean isPasswordMatches = bcrypt.matches(password, encrypted);
		
		if(!isPasswordMatches)
			throw new Exception("Password does not match.");
		
		return password;
	}
}
