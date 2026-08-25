package com.example.pc_management_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pc_management_app.entity.PcSoft;
import com.example.pc_management_app.repository.PcSoftRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PcSoftService {
	private final PcSoftRepository pcSoftRepository;

	//PCとソフトウェアの紐付けを新規作成する（登録用）
    public void createPcSoftLinks(Long pcId, List<Long> softwareIds) {
        List<PcSoft> pcSoftList = softwareIds.stream()
                .map(softId -> PcSoft.builder().pcId(pcId).softId(softId).build())
                .toList();
        pcSoftRepository.saveAll(pcSoftList);
    }

    //PCとソフトウェアの紐付けを置き換える（更新用：既存削除→再作成）
    public void replacePcSoftLinks(Long pcId, List<Long> softwareIds) {
        pcSoftRepository.deleteByPcId(pcId);
        pcSoftRepository.flush();
        createPcSoftLinks(pcId, softwareIds);
    }
    
    public List<Long> findPcIdsBySoftwareName(String softwareName) {
		return pcSoftRepository.findPcIdsBySoftwareName(softwareName);
	}
}