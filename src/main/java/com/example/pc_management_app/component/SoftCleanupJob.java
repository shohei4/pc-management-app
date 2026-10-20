package com.example.pc_management_app.component;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SoftCleanupJob {
	private final JdbcTemplate jdbcTemplate;

	@Scheduled(cron = "0 0 3 * * *") // 毎日3時
	public void cleanup() {
		while (true) {
			int deleted = jdbcTemplate.update("""
					DELETE FROM soft
					WHERE soft_id IN (
					    SELECT s.soft_id FROM soft s
					    WHERE NOT EXISTS (
					        SELECT 1 FROM pc_soft ps WHERE ps.soft_id = s.soft_id)
					    LIMIT 1000
					    FOR UPDATE SKIP LOCKED
					)
					""");
			if (deleted == 0)
				break;
		}

	}
}
