package com.example.pc_management_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc_management_app.entity.Pc;

@Repository
public interface PcRepository extends JpaRepository<Pc, Long> {
	
}
