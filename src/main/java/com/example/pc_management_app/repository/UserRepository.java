package com.example.pc_management_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc_management_app.entity.AuthUser;

@Repository
public interface UserRepository extends JpaRepository<AuthUser,Long>{

	Optional<AuthUser> findByUsername(String username);
	
}
