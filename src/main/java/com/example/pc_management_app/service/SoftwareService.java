package com.example.pc_management_app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.pc_management_app.entity.Software;
import com.example.pc_management_app.repository.SoftwareRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoftwareService {
	private final SoftwareRepository softwareRepository;

	//ソフトウェア名リストからsoftwareIdリストを解決する（既存流用 or 新規作成）
	public List<Long> resolveSoftwareIds(List<String> softwareNames) {
		List<String> safeNames = Optional.ofNullable(softwareNames)
				.orElse(Collections.emptyList());

		return safeNames.stream()
				.map(this::resolveSoftwareId)
				.toList();
	}

	//ソフトウェア名1件から既存のソフトウェアか判断し新規の場合は登録しidを返し、そうでない場合はそのままidを返す
	private Long resolveSoftwareId(String name) {
		return softwareRepository.findByName(name)
				.orElseGet(() -> softwareRepository.save(
						Software.builder().name(name).build()))
				.getId();
	}
}
