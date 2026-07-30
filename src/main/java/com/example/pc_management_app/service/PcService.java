package com.example.pc_management_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.pc_management_app.entity.Pc;
import com.example.pc_management_app.repository.PcRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PcService {
	
	private final PcRepository pcRepository;
	
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
	public Pc register(PcRegistrationRequest request) {
	}

}
