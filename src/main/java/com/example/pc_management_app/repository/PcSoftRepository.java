package com.example.pc_management_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pc_management_app.entity.PcSoft;

@Repository
public interface PcSoftRepository extends JpaRepository<PcSoft, Long> {
	List<PcSoft> findByPcId(Long pcId);
	
	List<PcSoft> findBySoftId(Long id);
	
	//ソフトウェア名からpcIdを検索してリストで返す
	@Query("SELECT ps.pcId FROM PcSoft ps \r\n"
			+ "JOIN Software s ON s.id = ps.softId \r\n"
			+ "WHERE s.name = :softwareName")
	List<Long> findPcIdsBySoftwareName(@Param("softwareName") String softwareName);
	
	void deleteByPcId(Long pcId);
}
