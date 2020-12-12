package com.enigma.agrox.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.agrox.dtos.FarmerDto;
import com.enigma.agrox.exceptions.DatabaseException;
import com.enigma.agrox.exceptions.ResourceNotFoundException;
import com.enigma.agrox.exceptions.ValidationException;
import com.enigma.agrox.models.FarmerModel;
import com.enigma.agrox.repos.FarmerRepo;
import com.enigma.agrox.validators.FarmerValidator;

@RestController
public class FarmerController {
	
	@Autowired
	FarmerRepo farmerRepo;
	
	@CrossOrigin
	@RequestMapping("/v1/farmers")
	public List<FarmerDto> getFarmers() {
		
		List<FarmerModel> farmerModels = (List<FarmerModel>) farmerRepo.findAll();
		List<FarmerDto> farmerDtos = new ArrayList<FarmerDto>();
		
		for (FarmerModel farmerModel:farmerModels) {
			FarmerDto farmerDto = new FarmerDto();
			
			farmerDto.setFarmerId(farmerModel.getFarmer_id());
			farmerDto.setFarmerContactNumber(farmerModel.getFarmer_contact_number());
			farmerDto.setFarmerLocation(farmerModel.getFarmer_location());
			farmerDto.setFarmerName(farmerModel.getFarmer_name());
			farmerDto.setFarmerPassword(farmerModel.getFarmer_password());
			farmerDto.setFarmerUsername(farmerModel.getFarmer_username());
			
			farmerDtos.add(farmerDto);// udin loop eken one by one fill karapu eka list ekkata add kara 
		}
		
		return farmerDtos;
	}
	
	@CrossOrigin
	@RequestMapping("/v1/farmer/{id}")//get type rest calls, rest service eken api illana ewa 
	public FarmerDto getFarmerById(@PathVariable int id) {
		
		FarmerDto farmerDto = new FarmerDto();
		
		try {
			FarmerModel farmerModel = (FarmerModel) farmerRepo.getOne(id);
			
			
			farmerDto.setFarmerId(farmerModel.getFarmer_id());
			farmerDto.setFarmerContactNumber(farmerModel.getFarmer_contact_number());
			farmerDto.setFarmerLocation(farmerModel.getFarmer_location());
			farmerDto.setFarmerName(farmerModel.getFarmer_name());
			farmerDto.setFarmerPassword(farmerModel.getFarmer_password());
			farmerDto.setFarmerUsername(farmerModel.getFarmer_username());
			return farmerDto; 
			
		} catch (Exception e) {
			throw new ResourceNotFoundException("Id not found");
			
		}
		
		
	}
	
	//new farmer knk add kirima
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/v1/farmer")
	public void addFarmer(@RequestBody FarmerDto farmerDto) {
		
		FarmerModel ExfarmerModel = (FarmerModel) farmerRepo.findByusername(farmerDto.getFarmerUsername());
		
		if (ExfarmerModel != null) {
			throw new ValidationException("Username already exist"); // existing farmer kenekta aya create karanna denne nathi eka
		}
		
		
		FarmerValidator.validateName(farmerDto.getFarmerName());
		FarmerValidator.validateContactNumber(farmerDto.getFarmerContactNumber());
		FarmerValidator.validateLocation(farmerDto.getFarmerLocation());
		FarmerValidator.validatePassword(farmerDto.getFarmerPassword());
		FarmerValidator.validateUsername(farmerDto.getFarmerUsername());
		
		
		FarmerModel farmerModel = new FarmerModel();// farmer model ek object ekak sadima
		
		//no need to mention farmer id here cos its auto increment
		farmerModel.setFarmer_contact_number(farmerDto.getFarmerContactNumber());
		farmerModel.setFarmer_location(farmerDto.getFarmerLocation());
		farmerModel.setFarmer_name(farmerDto.getFarmerName());
		farmerModel.setFarmer_password(farmerDto.getFarmerPassword());
		farmerModel.setFarmer_username(farmerDto.getFarmerUsername());
		
		try {
			farmerRepo.save(farmerModel);//dto eka awa eka model ekata damma after db ekata save kara
			
		} catch (Exception e) {
			throw new DatabaseException("Failed to add new farmer");
		}
		
		
	}
	
	//update farmer
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/v1/farmer")
	public void updateFarmer(@RequestBody FarmerDto farmerDto) {
		
		FarmerValidator.validateName(farmerDto.getFarmerName());
		FarmerValidator.validateContactNumber(farmerDto.getFarmerContactNumber());
		FarmerValidator.validateLocation(farmerDto.getFarmerLocation());
		FarmerValidator.validatePassword(farmerDto.getFarmerPassword());
		
		try {
			FarmerModel farmerModel = (FarmerModel) farmerRepo.getOne(farmerDto.getFarmerId());
			
			farmerModel.setFarmer_contact_number(farmerDto.getFarmerContactNumber());
			farmerModel.setFarmer_location(farmerDto.getFarmerLocation());
			farmerModel.setFarmer_name(farmerDto.getFarmerName());
			farmerModel.setFarmer_password(farmerDto.getFarmerPassword());
			farmerModel.setFarmer_username(farmerDto.getFarmerUsername());
			
			farmerRepo.save(farmerModel);//dto eka awa eka model ekata damma after db ekata save kara
			
			
		} catch (Exception e) {
			throw new ResourceNotFoundException("Farmer not found)");
		}
		
	}
	
	/**
	 * Delete farmer from the database for a given Id
	 * @param id
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/v1/farmer/{id}")
	public void deleteFarmer(@PathVariable int id) {
		
		try {
			FarmerModel farmerModel = (FarmerModel) farmerRepo.getOne(id);
			farmerRepo.delete(farmerModel);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Farmer not found)");
		}
		
	}
	
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/v1/farmer/login")
	public boolean loginFarmer(@RequestBody FarmerDto farmerDto) {
		FarmerModel farmerModel = (FarmerModel) farmerRepo.findByusername(farmerDto.getFarmerUsername());
		
		if(farmerModel.getFarmer_password() .equals(farmerDto.getFarmerPassword())) {
			return true;
		}else {
		
			return false;
			
		}
		
	}
	
	
}
