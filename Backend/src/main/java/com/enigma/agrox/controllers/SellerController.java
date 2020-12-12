package com.enigma.agrox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.agrox.dtos.SellerDto;
import com.enigma.agrox.exceptions.DatabaseException;
import com.enigma.agrox.exceptions.ResourceNotFoundException;
import com.enigma.agrox.exceptions.ValidationException;
import com.enigma.agrox.models.FarmerModel;
import com.enigma.agrox.models.SellerModel;
import com.enigma.agrox.repos.SellerRepo;
import com.enigma.agrox.validators.SellerValidator;

@RestController
public class SellerController {
	
	@Autowired
	SellerRepo sellerRepo;
	
	@CrossOrigin
	@RequestMapping("/v1/seller/{id}")
	public SellerDto getSellerById(@PathVariable int id) {
		
		SellerDto sellerDto = new SellerDto();
		
		try {
			SellerModel sellerModel = (SellerModel) sellerRepo.getOne(id);
			
			
			sellerDto.setSellerId(sellerModel.getSeller_id());
			sellerDto.setSellerPassword(sellerModel.getSeller_password());
			sellerDto.setSellerUsername(sellerModel.getSeller_username());
			return sellerDto; 
			
		} catch (Exception e) {
			
			throw new ResourceNotFoundException("Id not found");
		}
		
	}
	
	
	//Add new seller
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/v1/seller")
	public void addSeller(@RequestBody SellerDto sellerDto) {
		
		SellerModel ExsellerModel = (SellerModel) sellerRepo.findByusername(sellerDto.getSellerUsername());
		
		if (ExsellerModel != null) {
			throw new ValidationException("Username already exist"); // existing seller kenekta aya create karanna denne nathi eka
		}
		
		SellerValidator.validateUsername(sellerDto.getSellerUsername());
		SellerValidator.validatePassword(sellerDto.getSellerPassword());
		
		
		SellerModel sellerModel = new SellerModel();
		
		sellerModel.setSeller_password(sellerDto.getSellerPassword());
		sellerModel.setSeller_username(sellerDto.getSellerUsername());
		
		try {
			sellerRepo.save(sellerModel);
			
			
		} catch (Exception e) {
			throw new DatabaseException("Failed to add new seller");
		}
		
		
	}
	
	//uadate seller
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/v1/seller")
	public void updateSeller(@RequestBody SellerDto sellerDto) {
		
		SellerValidator.validatePassword(sellerDto.getSellerPassword());
		
		try {
			SellerModel sellerModel = (SellerModel) sellerRepo.getOne(sellerDto.getSellerId());
			
			sellerModel.setSeller_password(sellerDto.getSellerPassword());
			sellerModel.setSeller_username(sellerDto.getSellerUsername());
			
			sellerRepo.save(sellerModel);
			
			
		} catch (Exception e) {
			throw new ResourceNotFoundException("Seller not found)");
		}
		
	}
	
	
	//delete seller
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/v1/seller/{id}")
	public void deleteSeller(@PathVariable int id) {
		
		try {
			SellerModel sellerModel = (SellerModel) sellerRepo.getOne(id);
			sellerRepo.delete(sellerModel);
			
		} catch (Exception e) {
			throw new ResourceNotFoundException("Seller not found)");
		}
		
	}
	
	//Login seller
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/v1/seller/login")
	public boolean loginSeller(@RequestBody SellerDto sellerDto) {
		SellerModel sellerModel = (SellerModel) sellerRepo.findByusername(sellerDto.getSellerUsername());
		
		if (sellerModel.getSeller_password() .equals(sellerDto.getSellerPassword())) {
			return true;
		
		}else {
			
			return false;
		}
	}
	
}
	
