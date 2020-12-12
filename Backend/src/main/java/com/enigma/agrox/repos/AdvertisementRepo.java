package com.enigma.agrox.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.agrox.models.AdvertisementModel;
import com.enigma.agrox.models.FarmerModel;

public interface AdvertisementRepo extends JpaRepository<AdvertisementModel, Integer> {

	List<AdvertisementModel> findByreviewed(boolean reviewed);
	
	
	@Query("select a from AdvertisementModel a where a.ad_type =:adtype")
	List <AdvertisementModel> findByadtype(@Param("adtype")String adtype);
	

	
}
