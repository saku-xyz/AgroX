package com.enigma.agrox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.agrox.dtos.SystemAdminDto;
import com.enigma.agrox.exceptions.DatabaseException;
import com.enigma.agrox.exceptions.ResourceNotFoundException;
import com.enigma.agrox.models.AdvertisementModel;
import com.enigma.agrox.models.SystemAdminModel;
import com.enigma.agrox.repos.AdvertisementRepo;
import com.enigma.agrox.repos.SystemAdminRepo;
import com.enigma.agrox.validators.SystemAdminValidator;

@RestController
public class SystemAdminController {
	
	@Autowired
	SystemAdminRepo systemAdminRepo;
	
	@Autowired
	AdvertisementRepo advertisementRepo;
	
	//Add new admin
		@CrossOrigin
		@RequestMapping(method = RequestMethod.POST, value = "/v1/admin")
		public void addSystemAdmin(@RequestBody SystemAdminDto systemAdminDto) {
			
			
			SystemAdminValidator.validateUsername(systemAdminDto.getAdminUsername());
			SystemAdminValidator.validatePassword(systemAdminDto.getAdminPassword());
			
			SystemAdminModel systemAdminModel = new SystemAdminModel();
			
			systemAdminModel.setAdmin_password(systemAdminDto.getAdminPassword());
			systemAdminModel.setAdmin_username(systemAdminDto.getAdminUsername());
			
			try {
				systemAdminRepo.save(systemAdminModel);
				
				
			} catch (Exception e) {
				throw new DatabaseException("Failed to add an Admin");
			}
			
			
		}
		
		
		//update advertisement by admin
		@CrossOrigin
		@RequestMapping("/v1/admin/accept/{id}") 
		public void acceptById(@PathVariable int id) {
		
			
			try {
				AdvertisementModel advertisementModel = (AdvertisementModel) advertisementRepo.getOne(id);
				
				advertisementModel.setReviewed(true);
				
				advertisementRepo.save(advertisementModel);	
				
			} catch (Exception e) {
				
				throw new ResourceNotFoundException("Id not found");
			}
			
		}
		
		
		@CrossOrigin
		@RequestMapping(method = RequestMethod.GET, value = "/v1/admin/login")
		public boolean loginAdmin(@RequestBody  SystemAdminDto systemAdminDto) {
			SystemAdminModel systemAdminModel = (SystemAdminModel) systemAdminRepo.findByusername(systemAdminDto.getAdminUsername());
			
			if(systemAdminModel.getAdmin_password().equals(systemAdminDto.getAdminPassword())) {
				return true;
			}else {
			
				return false;
				
			}
			
		}
			
}
