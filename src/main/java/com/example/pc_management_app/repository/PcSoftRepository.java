package com.example.pc_management_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc_management_app.entity.PcSoft;

@Repository
public interface PcSoftRepository extends JpaRepository<PcSoft, Long>{
	Optional<PcSoft> findByPcId(Long pcId);
}
