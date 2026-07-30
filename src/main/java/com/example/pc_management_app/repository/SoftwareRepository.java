package com.example.pc_management_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc_management_app.entity.Software;

@Repository
public interface SoftwareRepository extends JpaRepository <Software, Long>{
	
}
