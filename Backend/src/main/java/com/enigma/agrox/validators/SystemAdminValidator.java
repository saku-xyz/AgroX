package com.enigma.agrox.validators;

import com.enigma.agrox.exceptions.ValidationException;

public class SystemAdminValidator {
	public static void validateUsername(String username) {
		if (username == null || username.equals(""))
			throw new ValidationException("Invalid Username");
	}
	
	
	public static void validatePassword(String password) {
		if (password == null || password.equals(""))
			throw new ValidationException("Invalid Password");
}
}
