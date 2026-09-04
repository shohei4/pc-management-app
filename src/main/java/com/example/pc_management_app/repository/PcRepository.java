package com.example.pc_management_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pc_management_app.entity.Pc;

@Repository
public interface PcRepository extends JpaRepository<Pc, Long> {
	//PC一覧を昇順取得
	List<Pc> findAllByOrderByPcNumberAsc();

	// PC番号で絞り込み
	Optional<Pc> findByPcNumber(String pcNumber);

	// 利用者属性で絞込
	List<Pc> findByUserAttr(String userAttr);

	// OSで絞り込み
	List<Pc> findByOs(String os);

	//キーワードで絞込
	@Query("SELECT p FROM Pc p WHERE " +
			"LOWER(p.pcNumber) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(p.userName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(p.maker) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(p.os) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
			"ORDER BY p.pcNumber ASC")
	List<Pc> searchByKeyword(@Param("keyword") String keyword);

	// ソフトで絞込
	@Query("SELECT DISTINCT p FROM Pc p " +
			"JOIN PcSoft ps ON ps.pcId = p.id " +
			"JOIN Software s ON s.id = ps.softId " +
			"WHERE s.name = :softwareName")
	List<Pc> findBySoftwareName(@Param("softwareName") String softwareName);

}
