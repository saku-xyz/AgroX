package com.enigma.agrox.validators;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
	
	public static void validateImageExt(MultipartFile filesList) {

		String fileExt = StringUtils.cleanPath(filesList.getOriginalFilename()).split("\\.")[1];
	       
        if(!("jpg".equals(fileExt))) {
               throw new ValidationException("Invalid image type");
           }
       }
		    
	public static void ValidateAdId(String AdId) {
		if (AdId == null || AdId.equals(""))
			throw new ValidationException("Invalid Ad Id");	
	}
}
