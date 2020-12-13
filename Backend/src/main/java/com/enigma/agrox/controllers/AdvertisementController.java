package com.enigma.agrox.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enigma.agrox.dtos.AdvertisementDto;
import com.enigma.agrox.dtos.FarmerDto;
import com.enigma.agrox.exceptions.DatabaseException;
import com.enigma.agrox.exceptions.FileStorageException;
import com.enigma.agrox.exceptions.MyFileNotFoundException;
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
	
	/**
	 * Retrive Advertisement by ID
	 * @param id = Advertisement Id
	 * @return Advertisement Dto with Advertisement details
	 */
	
	@CrossOrigin
	@RequestMapping("/v1/advertisement/{id}")
	public AdvertisementDto getAdvertisementById(@PathVariable int id) {
		
		AdvertisementDto advertisementDto = new AdvertisementDto();
		
		try {
			//Getting Advertisement Model from the database by giving ID
			AdvertisementModel advertisementModel = (AdvertisementModel) advertisementRepo.getOne(id); 
			
			//Transforming Model to Dto
			advertisementDto.setAdId(advertisementModel.getAd_id());
			advertisementDto.setAdDate(advertisementModel.getAd_date());
			advertisementDto.setAdTitle(advertisementModel.getAd_title());
			advertisementDto.setAdQuantity(advertisementModel.getAd_quantity());
			advertisementDto.setAdPrice(advertisementModel.getAd_price());
			advertisementDto.setAdType(advertisementModel.getAd_type());
			advertisementDto.setAdReviewed(advertisementModel.getReviewed());
			advertisementDto.setImgUrl("/v1/advertisement/img/" + advertisementModel.getAd_id() + ".jpg");
			
			//Get the farmer model(foreign key)
			FarmerModel farmerModel = advertisementModel.getFarmer_id();
			FarmerDto farmerDto = new FarmerDto();
			
			farmerDto.setFarmerId(farmerModel.getFarmer_id());
			farmerDto.setFarmerContactNumber(farmerModel.getFarmer_contact_number());
			farmerDto.setFarmerLocation(farmerModel.getFarmer_location());
			farmerDto.setFarmerName(farmerModel.getFarmer_name());
			
			
			advertisementDto.setFarmer(farmerDto);
			return advertisementDto; 
			
			
		} catch (Exception e) {
			throw new ResourceNotFoundException("Advertisement id not found");
		}
		
		
		
	
	}

	/**
	 * Displaying all the approved Advertisements in the home page
	 * @return Advertisement dto with Advertisement details
	 */
	
	
	@CrossOrigin
	@RequestMapping("/v1/advertisement/approved")
	public List<AdvertisementDto> getAcceptedAdvertisement() {
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
			advertisementDto.setImgUrl("/v1/advertisement/img/" + advertisementModel.getAd_id() + ".jpg");
			
			FarmerModel farmerModel = advertisementModel.getFarmer_id();
			FarmerDto farmerDto = new FarmerDto();
			
			farmerDto.setFarmerId(farmerModel.getFarmer_id());
			farmerDto.setFarmerContactNumber(farmerModel.getFarmer_contact_number());
			farmerDto.setFarmerLocation(farmerModel.getFarmer_location());
			farmerDto.setFarmerName(farmerModel.getFarmer_name());
			
			
			advertisementDto.setFarmer(farmerDto);
			advertisementDtos.add(advertisementDto);
			
		}
		
		return advertisementDtos;
		}
	
	@CrossOrigin
	@RequestMapping("/v1/advertisement/rejected")
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
			advertisementDto.setImgUrl("/v1/advertisement/img/" + advertisementModel.getAd_id() + ".jpg");
			
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
		public int addAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
			
		
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
				return advertisementModel.getAd_id();
				
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
				advertisementDto.setImgUrl("/v1/advertisement/img/" + advertisementModel.getAd_id() + ".jpg");
				
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

		// posting/uploading image to add
		@CrossOrigin
	    @PostMapping("/v1/advertisement/uploadImages/{adID}")//post method ; sending data by body, image file & id
	    public void uploadAdvertisementImgs(@RequestParam("files") MultipartFile[] files,@PathVariable String adID ) {
	    	try {
		        List<MultipartFile> filesList =  Arrays.asList(files);
		        
		        AdvertisementValidator.ValidateAdId(adID);
		        //AdvertisementValidator.validateImageExt(files);
		        
		        for(MultipartFile file : filesList) {
		        	//Get file ext
		        	String fileExt = StringUtils.cleanPath(file.getOriginalFilename()).split("\\.")[1];
		        	//Generate img name (adID_count.ext)
		        	String fileName = adID +  "." + fileExt;
		        	//Store file
		        	storeFile(file, fileName);
		        }
		        
		        
	    		} catch (FileStorageException fse) {
	    			throw new DatabaseException("Failed to Upload the image");
	    		}
			}
		
		
		
		@CrossOrigin
		@GetMapping("/v1/advertisement/img/{fileName:.+}")
		public ResponseEntity<Resource> downloadAdImg(@PathVariable String fileName, HttpServletRequest request) {
		    // Load file as Resource
		    Resource resource = loadFileAsResource(fileName);

		    return getResource(request, resource);
		}

		
		//getting the uploaded image
		public Resource loadFileAsResource(String fileName) {
	       try {
	    	   Path imgStorageLocation = Paths.get("/adImgs").toAbsolutePath().normalize();   
	    	   Path filePath = imgStorageLocation.resolve(fileName).normalize();
	           
	    	   Resource resource = new UrlResource(filePath.toUri());
	           if(resource.exists()) {
	               return resource;
	           } else {
	               throw new MyFileNotFoundException("File not found " + fileName);
	           }
	           
	       	} catch (MalformedURLException ex) {
       			throw new MyFileNotFoundException("File not found " + fileName, ex);
       		}
	   	}
		
		
		
		private ResponseEntity<Resource> getResource(HttpServletRequest request, Resource resource) {
			// Try to determine file's content type
			String contentType = null;
	        try {
	           contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	           throw new MyFileNotFoundException("Could not determine file type.");
	        }

	       // Fallback to the default content type if type could not be determined
	       if(contentType == null) {
	           contentType = "application/octet-stream";
	       }

	       return ResponseEntity.ok()
	               .contentType(MediaType.parseMediaType(contentType))
	               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	               .body(resource);
		}

		
		
		private void storeFile(MultipartFile file, String fileName) {

			Path imgStorageLocation = Paths.get("/adImgs").toAbsolutePath().normalize();
			
	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new FileStorageException("Filename contains invalid path sequence " + fileName);
	            }
	            
	            Path targetLocation = imgStorageLocation.resolve(fileName);
	            
	            // Copy file to the target location (Replacing existing file with the same name)
	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

	            
	        	} catch (IOException ex) {
	        	System.out.println(ex);
	            throw new FileStorageException("Could not store file " + fileName, ex);
	        	}
			}
		
		
		}

