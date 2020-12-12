package com.enigma.agrox.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.agrox.models.SellerModel;

public interface SellerRepo extends JpaRepository<SellerModel, Integer> {

	@Query("select s from SellerModel s where s.seller_username =:username")
	SellerModel findByusername(@Param("username")String username);
}
