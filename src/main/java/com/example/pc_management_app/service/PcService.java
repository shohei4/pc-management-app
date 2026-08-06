package com.example.pc_management_app.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.pc_management_app.dto.PcListItemDto;
import com.example.pc_management_app.dto.PcRequest;
import com.example.pc_management_app.entity.Pc;
import com.example.pc_management_app.entity.PcSoft;
import com.example.pc_management_app.entity.Software;
import com.example.pc_management_app.mapper.PcMapper;
import com.example.pc_management_app.repository.PcRepository;
import com.example.pc_management_app.repository.PcSoftRepository;
import com.example.pc_management_app.repository.SoftwareRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PcService {

	private final PcRepository pcRepository;
	private final PcSoftRepository pcSoftRepository;
	private final SoftwareRepository softwareRepository;
	private final PcMapper pcMapper;

	//一覧取得
	public List<PcListItemDto> findAllPc() {
		return pcRepository.findAll().stream()
				.map(pcMapper::toListItemDto)
				.toList();
	}

	// 既存の一覧取得メソッド（DTO変換）
	public List<PcListItemDto> findAllPcForList() {
		return pcRepository.findAll().stream()
				.map(pcMapper::toListItemDto)
				.toList();
	}

	//PC番号で絞込
	public PcListItemDto findByPcNumber(int pcNumber) {
		Pc pc = pcRepository.findByPcNumber(pcNumber)
				.orElseThrow(() -> new EntityNotFoundException("PC番号が見つかりません: " + pcNumber));

		return pcMapper.toListItemDto(pc);
	}

	//利用者属性で絞込
	public List<PcListItemDto> findByUserAttr(String userAttr) {
		return pcRepository.findByUserAttr(userAttr).stream()
				.map(pcMapper::toListItemDto)
				.toList();
	}

	// 一覧表示用（属性ごとにグルーピング）
	public Map<String, List<PcListItemDto>> findAllPcForListGroupedByUserAttr() {
		return findAllPcForList().stream()
				.collect(Collectors.groupingBy(PcListItemDto::getUserAttr));
	}

	//OSで絞込
	public List<PcListItemDto> findByOs(String os) {
		return pcRepository.findByOs(os).stream()
				.map(pcMapper::toListItemDto)
				.toList();
	}

	//ソフトで絞込
	public List<PcListItemDto> findBySoftwareName(String softwareName) {
		
		List<Long> pcIds = pcSoftRepository.findPcIdsBySoftwareName(softwareName);
		if (pcIds.isEmpty()) {
			throw new EntityNotFoundException("ソフトウェア名が見つかりません: " + softwareName);
		}
		
		return pcRepository.findAllById(pcIds).stream()
				.map(pcMapper::toListItemDto)
				.toList();

	}

	//登録処理
	@Transactional
	public void register(PcRequest request) {
		// 1. PCテーブルへの登録
		Pc pc = Pc.builder()
				.pcNumber(request.getPcNumber())
				.userName(request.getUserName())
				.userAttr(request.getUserAttr())
				.maker(request.getMaker())
				.os(request.getOs())
				.remarks(request.getRemarks())
				.build();

		Pc savedPc = pcRepository.save(pc);

		//2. Softwareテーブルへの登録（既存流用 or 新規作成）
		List<Long> softwareIds = request.getSavedSoftwareNames().stream()
				.map(name -> softwareRepository.findByName(name)
						.orElseGet(() -> softwareRepository.save( //findByNameにヒットしない場合登録を行う
								Software.builder().name(name).build()))
						.getId())
				.toList();

		// 3. softwareIds を使って PcSoft テーブルへの登録
		List<PcSoft> pcSoftList = softwareIds.stream()
				.map(softId -> PcSoft.builder()
						.pcId(savedPc.getId())
						.softId(softId)
						.build())
				.toList();
		pcSoftRepository.saveAll(pcSoftList);
	}

}
