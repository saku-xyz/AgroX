package com.enigma.agrox.validators;

import com.enigma.agrox.exceptions.ValidationException;

public class FarmerValidator {
	public static void validateName(String name) {
		if (name == null || name.equals(""))
			throw new ValidationException("Invalid Farmer Name");
	}
	
	public static void validateContactNumber(String contactnumber) {
		if (contactnumber == null || contactnumber.equals(""))
			throw new ValidationException("Invalid Contact Number");
	}
	
	
	public static void validateLocation(String location) {
		if (location == null || location.equals(""))
			throw new ValidationException("Invalid Location");
	}
	
	
	public static void validateUsername(String username) {
		if (username == null || username.equals(""))
			throw new ValidationException("Invalid Username");
	}
	
	
	public static void validatePassword(String password) {
		if (password == null || password.equals(""))
			throw new ValidationException("Invalid Password");
	}
}
