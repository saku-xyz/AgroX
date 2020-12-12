package com.enigma.agrox.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.agrox.models.FarmerModel;

public interface FarmerRepo extends JpaRepository<FarmerModel, Integer> {
	
	@Query("select f from FarmerModel f where f.farmer_username =:username")
	FarmerModel findByusername(@Param("username")String username);
}
