package com.example.pc_management_app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.pc_management_app.dto.PcRequest;
import com.example.pc_management_app.entity.Pc;
import com.example.pc_management_app.entity.PcSoft;
import com.example.pc_management_app.repository.PcRepository;
import com.example.pc_management_app.repository.PcSoftRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PcService {
	
	private final PcRepository pcRepository;
	private final PcSoftRepository pcSoftRepository;
	
	//一覧取得
	public List<Pc> findAllPc(){
			return pcRepository.findAll();
		}

	//PC番号で絞込
	public Optional<Pc> findByPcNumber(int pcNumber){
		return pcRepository.findByPcNumber(pcNumber);
	}

	//利用者属性で絞込
	public List<Pc> findByUserAttr(String userAttr){
		return pcRepository.findByUserAttr(userAttr);
	}

	//OSで絞込
	public List<Pc> findByOs(String os){
		return pcRepository.findByOs(os);
	}

	//ソフトで絞込
	public List<Pc> findBySoftwareName(String softwareName){
		return pcRepository.findBySoftwareName(softwareName);
	}
	
	//登録処理
	@Transactional
	public void register(PcRequest request) {
		//PCテーブルへの登録
		Pc pc = Pc.builder()
				.pcNumber(request.getPcNumber())
				.userName(request.getUserName())
				.userAttr(request.getUserAttr())
				.maker(request.getMaker())
				.os(request.getOs())
				.remarks(request.getRemarks())
				.build();
		
		Pc savedPc = pcRepository.save(pc);
		
		//PC-Softテーブルへの登録
		List<PcSoft> pcSoftList = request.getSelectedSoftwareIds().stream()
		        .map(softId -> PcSoft.builder()
		                .pcId(savedPc.getId())
		                .softId(softId)
		                .build())
		        .collect(Collectors.toList());
		
		pcSoftRepository.saveAll(pcSoftList);
	}

}
