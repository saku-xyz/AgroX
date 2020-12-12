package com.enigma.agrox.validators;

import com.enigma.agrox.exceptions.ValidationException;

public class AdvertisementValidator {
	public static void validateTitle(String title) {
		if (title == null || title.equals(""))
			throw new ValidationException("Invalid Title");
	}
	
	public static void validateDate(String date) {
		if (date == null || date.equals(""))
			throw new ValidationException("Invalid Date");
	}
	
	
	public static void validateQuantity(String quantity) {
		if (quantity == null || quantity.equals(""))
			throw new ValidationException("Invalid Quantity");
	}
	
	
	public static void validatePrice(float price) {
		if (price <= 0)
			throw new ValidationException("Invalid Price");
	}
	
	
	public static void validateType(String type) {
		if (type == null || type.equals(""))
			throw new ValidationException("Invalid Type");
	}
	
	public static void validateFarmerId(int farmerid) {
		if (farmerid <= 0)
			throw new ValidationException("Invalid Farmer");
	}
}
