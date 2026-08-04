package com.example.pc_management_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc_management_app.entity.Software;

@Repository
public interface SoftwareRepository extends JpaRepository <Software, Long>{
	//ソフト名から検索
	Optional<Software> findByName(String name);
}
