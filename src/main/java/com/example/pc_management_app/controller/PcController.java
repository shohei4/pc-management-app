package com.example.pc_management_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pc_management_app.dto.PcListItemDto;
import com.example.pc_management_app.service.PcService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/pcs")
@RequiredArgsConstructor
public class PcController {
	
	private final PcService pcService;
	
	//一覧表示
	@GetMapping
	public String  list(Model model) {
		Map<String, List<PcListItemDto>> groupedPcs = pcService.findAllPcForListGroupedByUserAttr();

        model.addAttribute("groupedPcs", groupedPcs);

        return "pc/list";
	}
}
