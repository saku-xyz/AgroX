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

import com.enigma.agrox.dtos.AdvertisementDto;
import com.enigma.agrox.dtos.FarmerDto;
import com.enigma.agrox.exceptions.DatabaseException;
import com.enigma.agrox.exceptions.ResourceNotFoundException;
import com.enigma.agrox.models.AdvertisementModel;
import com.enigma.agrox.models.FarmerModel;
import com.enigma.agrox.repos.AdvertisementRepo;
import com.enigma.agrox.repos.FarmerRepo;
import com.enigma.agrox.validators.AdvertisementValidator;


@RestController
public class AdvertisementController {
	
	@Autowired
	AdvertisementRepo advertisementRepo;
	@Autowired 
	FarmerRepo farmerRepo;
	
	@CrossOrigin
	@RequestMapping("/v1/advertisement/{id}")
	public AdvertisementDto getAdvertisementById(@PathVariable int id) {
		
		AdvertisementDto advertisementDto = new AdvertisementDto();
		
		try {
			AdvertisementModel advertisementModel = (AdvertisementModel) advertisementRepo.getOne(id);
			
			advertisementDto.setAdId(advertisementModel.getAd_id());
			advertisementDto.setAdDate(advertisementModel.getAd_date());
			advertisementDto.setAdTitle(advertisementModel.getAd_title());
			advertisementDto.setAdQuantity(advertisementModel.getAd_quantity());
			advertisementDto.setAdPrice(advertisementModel.getAd_price());
			advertisementDto.setAdType(advertisementModel.getAd_type());
			advertisementDto.setAdReviewed(advertisementModel.getReviewed());
			
			FarmerModel farmerModel = advertisementModel.getFarmer_id();//ad eke inna farmer model
			FarmerDto farmerDto = new FarmerDto();// foreign key eka farmer model eka
			
			farmerDto.setFarmerId(farmerModel.getFarmer_id());
			farmerDto.setFarmerContactNumber(farmerModel.getFarmer_contact_number());
			farmerDto.setFarmerLocation(farmerModel.getFarmer_location());
			farmerDto.setFarmerName(farmerModel.getFarmer_name());
			//farmerDto.setFarmerPassword(farmerModel.getFarmer_password()); no need to give farmer username & password to clients
			//farmerDto.setFarmerUsername(farmerModel.getFarmer_username());
			
			advertisementDto.setFarmer(farmerDto);
			return advertisementDto; 
			
			
		} catch (Exception e) {
			throw new ResourceNotFoundException("Advertisement id not found");
		}
		
		
		
	
	}

	
	//siyaluma accepted ads ganna puluwan rest service eka
	
	@CrossOrigin
	@RequestMapping("/v1/advertisement/approved")
	//@RequestMapping(method = RequestMethod.POST, value = "/v1/advertisement/approved")
	public List<AdvertisementDto> getAcceptedAdvertisement() {// accepted kiyla url eka dige enne naa eka nisa no need to puyt path variable
		List<AdvertisementModel> advertisementModels = (List<AdvertisementModel>)advertisementRepo.findByreviewed(true);
		List<AdvertisementDto> advertisementDtos = new ArrayList<AdvertisementDto>();
		
		for (AdvertisementModel advertisementModel:advertisementModels) {
			AdvertisementDto advertisementDto = new AdvertisementDto();
			
			advertisementDto.setAdId(advertisementModel.getAd_id());
			advertisementDto.setAdDate(advertisementModel.getAd_date());
			advertisementDto.setAdPrice(advertisementModel.getAd_price());
			advertisementDto.setAdQuantity(advertisementModel.getAd_quantity());
			advertisementDto.setAdReviewed(advertisementModel.getReviewed());
			advertisementDto.setAdTitle(advertisementModel.getAd_title());
			advertisementDto.setAdType(advertisementModel.getAd_type());
			
			FarmerModel farmerModel = advertisementModel.getFarmer_id();//ad eke inna farmer model
			FarmerDto farmerDto = new FarmerDto();// foreign key eka farmer model eka
			
			farmerDto.setFarmerId(farmerModel.getFarmer_id());
			farmerDto.setFarmerContactNumber(farmerModel.getFarmer_contact_number());
			farmerDto.setFarmerLocation(farmerModel.getFarmer_location());
			farmerDto.setFarmerName(farmerModel.getFarmer_name());
			//farmerDto.setFarmerPassword(farmerModel.getFarmer_password()); no need to give farmer username & password to clients
			//farmerDto.setFarmerUsername(farmerModel.getFarmer_username());
			
			advertisementDto.setFarmer(farmerDto);
			advertisementDtos.add(advertisementDto);
			
		}
		
		return advertisementDtos;
	}
	
	@CrossOrigin
	@RequestMapping("/v1/advertisement/rejected")
	//@RequestMapping(method = RequestMethod.POST, value = "/v1/advertisement/rejected")
	public List<AdvertisementDto> getRejectedAdvertisement() {// accepted kiyla url eka dige enne naa eka nisa no need to puyt path variable
		List<AdvertisementModel> advertisementModels = (List<AdvertisementModel>)advertisementRepo.findByreviewed(false);
		List<AdvertisementDto> advertisementDtos = new ArrayList<AdvertisementDto>();
		
		for (AdvertisementModel advertisementModel:advertisementModels) {
			AdvertisementDto advertisementDto = new AdvertisementDto();
			
			advertisementDto.setAdId(advertisementModel.getAd_id());
			advertisementDto.setAdDate(advertisementModel.getAd_date());
			advertisementDto.setAdPrice(advertisementModel.getAd_price());
			advertisementDto.setAdQuantity(advertisementModel.getAd_quantity());
			advertisementDto.setAdReviewed(advertisementModel.getReviewed());
			advertisementDto.setAdTitle(advertisementModel.getAd_title());
			advertisementDto.setAdType(advertisementModel.getAd_type());
			
			FarmerModel farmerModel = advertisementModel.getFarmer_id();//ad eke inna farmer model
			FarmerDto farmerDto = new FarmerDto();// foreign key eka farmer model eka
			
			farmerDto.setFarmerId(farmerModel.getFarmer_id());
			farmerDto.setFarmerContactNumber(farmerModel.getFarmer_contact_number());
			farmerDto.setFarmerLocation(farmerModel.getFarmer_location());
			farmerDto.setFarmerName(farmerModel.getFarmer_name());
			//farmerDto.setFarmerPassword(farmerModel.getFarmer_password()); no need to give farmer username & password to clients
			//farmerDto.setFarmerUsername(farmerModel.getFarmer_username());
			
			advertisementDto.setFarmer(farmerDto);
			advertisementDtos.add(advertisementDto);
			
		}
		
		return advertisementDtos;
	}
	
	//new advertisement knk add kirima
		@CrossOrigin
		@RequestMapping(method = RequestMethod.POST, value = "/v1/advertisement")
		public void addAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
			
		
			AdvertisementValidator.validateDate(advertisementDto.getAdDate());
			AdvertisementValidator.validateFarmerId(advertisementDto.getFarmer().getFarmerId());
			AdvertisementValidator.validatePrice(advertisementDto.getAdPrice());
			AdvertisementValidator.validateQuantity(advertisementDto.getAdQuantity());
			AdvertisementValidator.validateTitle(advertisementDto.getAdTitle());
			AdvertisementValidator.validateType(advertisementDto.getAdType());
			
			AdvertisementModel advertisementModel = new AdvertisementModel();// Advertisement model ek object ekak sadima
			
			//no need to mention Advertisement id here cos its auto increment
			advertisementModel.setAd_date(advertisementDto.getAdDate());
			advertisementModel.setAd_price(advertisementDto.getAdPrice());
			advertisementModel.setAd_quantity(advertisementDto.getAdQuantity());
			advertisementModel.setAd_title(advertisementDto.getAdTitle());
			advertisementModel.setAd_type(advertisementDto.getAdType());
			advertisementModel.setFarmer_id((FarmerModel)farmerRepo.getOne(advertisementDto.getFarmer().getFarmerId()));
			advertisementModel.setReviewed(false);
			
			try {
				advertisementRepo.save(advertisementModel);//dto eka awa eka model ekata damma after db ekata save kara
				
			} catch (Exception e) {
				throw new DatabaseException("Failed to add new advertisement");
			}
			
		}
	
		
		//update advertisement
		@CrossOrigin
		@RequestMapping(method = RequestMethod.PUT, value = "/v1/advertisement")
		public void updateAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
			
			AdvertisementValidator.validateDate(advertisementDto.getAdDate());
			AdvertisementValidator.validateFarmerId(advertisementDto.getFarmer().getFarmerId());
			AdvertisementValidator.validatePrice(advertisementDto.getAdPrice());
			AdvertisementValidator.validateQuantity(advertisementDto.getAdQuantity());
			AdvertisementValidator.validateTitle(advertisementDto.getAdTitle());
			AdvertisementValidator.validateType(advertisementDto.getAdType());
			
			try {
				AdvertisementModel advertisementModel = (AdvertisementModel) advertisementRepo.getOne(advertisementDto.getAdId());
				
				advertisementModel.setAd_date(advertisementDto.getAdDate());
				advertisementModel.setAd_price(advertisementDto.getAdPrice());
				advertisementModel.setAd_quantity(advertisementDto.getAdQuantity());
				advertisementModel.setAd_title(advertisementDto.getAdTitle());
				advertisementModel.setAd_type(advertisementDto.getAdType());
				advertisementModel.setFarmer_id((FarmerModel)farmerRepo.getOne(advertisementDto.getFarmer().getFarmerId()));
				advertisementModel.setReviewed(false);
				
			} catch (Exception e) {
				throw new ResourceNotFoundException("Advertisement not found)");
			}
			
		}
		
		
		@CrossOrigin
		@RequestMapping(method = RequestMethod.DELETE, value = "/v1/advertisement/{id}")
		public void deleteAdvertisement(@PathVariable int id) {
			
			try {
				AdvertisementModel advertisementModel = (AdvertisementModel) advertisementRepo.getOne(id);
				advertisementRepo.delete(advertisementModel);
			} catch (Exception e) {
				throw new ResourceNotFoundException("Advertisement not found)");
			}
			
		}
	
	


		//CATEGORY EKA FILTER KARANWA
		@CrossOrigin
		@RequestMapping("/v1/advertisement/category/{type}")
		public List <AdvertisementDto> getAdvertisementByCategory(@PathVariable String type) {
		
			List<AdvertisementModel> advertisementModels = (List<AdvertisementModel>)advertisementRepo.findByadtype(type);
			List<AdvertisementDto> advertisementDtos = new ArrayList<AdvertisementDto>();
			
			for (AdvertisementModel advertisementModel:advertisementModels) {
				AdvertisementDto advertisementDto = new AdvertisementDto();
				
				advertisementDto.setAdId(advertisementModel.getAd_id());
				advertisementDto.setAdDate(advertisementModel.getAd_date());
				advertisementDto.setAdPrice(advertisementModel.getAd_price());
				advertisementDto.setAdQuantity(advertisementModel.getAd_quantity());
				advertisementDto.setAdReviewed(advertisementModel.getReviewed());
				advertisementDto.setAdTitle(advertisementModel.getAd_title());
				advertisementDto.setAdType(advertisementModel.getAd_type());
				
				FarmerModel farmerModel = advertisementModel.getFarmer_id();//ad eke inna farmer model
				FarmerDto farmerDto = new FarmerDto();// foreign key eka farmer model eka
				
				farmerDto.setFarmerId(farmerModel.getFarmer_id());
				farmerDto.setFarmerContactNumber(farmerModel.getFarmer_contact_number());
				farmerDto.setFarmerLocation(farmerModel.getFarmer_location());
				farmerDto.setFarmerName(farmerModel.getFarmer_name());
				//farmerDto.setFarmerPassword(farmerModel.getFarmer_password()); no need to give farmer username & password to clients
				//farmerDto.setFarmerUsername(farmerModel.getFarmer_username());
				
				advertisementDto.setFarmer(farmerDto);
				advertisementDtos.add(advertisementDto);
				
			}
			
			return advertisementDtos;
			
		}

}

