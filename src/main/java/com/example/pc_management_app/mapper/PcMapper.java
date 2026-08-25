package com.example.pc_management_app.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.pc_management_app.dto.pc.PcListItemDto;
import com.example.pc_management_app.dto.pc.PcRequest;
import com.example.pc_management_app.entity.Pc;
import com.example.pc_management_app.entity.Software;
import com.example.pc_management_app.repository.PcSoftRepository;
import com.example.pc_management_app.repository.SoftwareRepository;

@Component
public class PcMapper {
	private final PcSoftRepository pcSoftRepository;
	private final SoftwareRepository softwareRepository;

	public PcMapper(PcSoftRepository pcSoftRepository, SoftwareRepository softwareRepository) {
		this.pcSoftRepository = pcSoftRepository;
		this.softwareRepository = softwareRepository;
	}

	public PcListItemDto toListItemDto(Pc pc) {
		List<String> softwareNames = pcSoftRepository.findByPcId(pc.getId()).stream()
				.map(pcSoft -> softwareRepository.findById(pcSoft.getSoftId())
						.map(Software::getName)
						.orElse(""))
				.toList();

		return new PcListItemDto(
				pc.getId(),
				pc.getPcNumber(),
				pc.getUserName(),
				pc.getUserAttr(),
				pc.getMaker(),
				pc.getOs(),
				pc.getRemarks(),
				softwareNames);
	}

	public PcRequest toRequestDto(Pc pc) {
		List<String> softwareNames = pcSoftRepository.findByPcId(pc.getId()).stream()
				.map(pcSoft -> softwareRepository.findById(pcSoft.getSoftId())
						.map(Software::getName)
						.orElse(""))
				.toList();
		return PcRequest.builder()
				.pcNumber(pc.getPcNumber())
				.userName(pc.getUserName())
				.userAttr(pc.getUserAttr())
				.maker(pc.getMaker())
				.os(pc.getOs())
				.remarks(pc.getRemarks())
				.softwareNames(softwareNames)
				.build();
	}
}
