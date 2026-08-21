package com.example.pc_management_app.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.pc_management_app.dto.PcListItemDto;
import com.example.pc_management_app.dto.PcRequest;
import com.example.pc_management_app.entity.Pc;
import com.example.pc_management_app.mapper.PcMapper;
import com.example.pc_management_app.repository.PcRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PcService {

	private final PcRepository pcRepository;
	private final PcSoftService pcSoftService;
	private final SoftwareService softwareService;
	private final PcMapper pcMapper;

	// 一覧取得メソッド（DTO変換）
	public List<PcListItemDto> findAllPcForList() {
		return pcRepository.findAll().stream()
				.map(pcMapper::toListItemDto)
				.toList();
	}
	
	//更新フォーム表示用（idを含めて取得）
	public PcRequest findById(Long pcId) {
	    Pc pc = pcRepository.findById(pcId)
	            .orElseThrow(() -> new EntityNotFoundException("当該PC情報が見つかりません: " + pcId));
	    return pcMapper.toRequestDto(pc); // MapperにEntity→PcRequestの変換を追加
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

		List<Long> pcIds = pcSoftService.findPcIdsBySoftwareName(softwareName);
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
	        Pc pc = Pc.builder()
	                .pcNumber(request.getPcNumber())
	                .userName(request.getUserName())
	                .userAttr(request.getUserAttr())
	                .maker(request.getMaker())
	                .os(request.getOs())
	                .remarks(request.getRemarks())
	                .build();
	        Pc savedPc = pcRepository.save(pc);

	        List<Long> softwareIds = softwareService.resolveSoftwareIds(request.getSoftwareNames());
	        pcSoftService.createPcSoftLinks(savedPc.getId(), softwareIds);
	    }

	//更新処理
	 @Transactional
	    public void update(PcRequest request, Long pcId) {
	        Pc targetPcItem = pcRepository.findById(pcId)
	                .orElseThrow(() -> new EntityNotFoundException("当該PC情報が見つかりません: " + pcId));

	        targetPcItem.setPcNumber(request.getPcNumber());
	        targetPcItem.setUserName(request.getUserName());
	        targetPcItem.setUserAttr(request.getUserAttr());
	        targetPcItem.setMaker(request.getMaker());
	        targetPcItem.setOs(request.getOs());
	        targetPcItem.setRemarks(request.getRemarks());
	        pcRepository.save(targetPcItem);

	        List<Long> softwareIds = softwareService.resolveSoftwareIds(request.getSoftwareNames());
	        pcSoftService.replacePcSoftLinks(pcId, softwareIds);
	    }

}
