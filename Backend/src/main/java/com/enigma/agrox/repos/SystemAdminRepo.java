package com.enigma.agrox.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.agrox.models.SystemAdminModel;

public interface SystemAdminRepo extends JpaRepository<SystemAdminModel, Integer> {
	@Query("select s from SystemAdminModel s where s.admin_username =:username")
	SystemAdminModel findByusername(@Param("username")String username);
}
