package com.example.pc_management_app.controller;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pc_management_app.dto.PcListItemDto;
import com.example.pc_management_app.dto.PcRequest;
import com.example.pc_management_app.service.PcService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/pcs")
@RequiredArgsConstructor
public class PcController {

	private final PcService pcService;

	//一覧表示
	@GetMapping
	public String list(Model model) {
		Map<String, List<PcListItemDto>> groupedPcs = pcService.findAllPcForListGroupedByUserAttr();

		model.addAttribute("groupedPcs", groupedPcs);

		return "pc/list";
	}

	//登録フォーム表示
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("pcRequest", new PcRequest());
		return "pc/register";
	}

	//登録処理
	@PostMapping
	public String save(@Valid @ModelAttribute PcRequest request,
			BindingResult bidingResult,
			RedirectAttributes  redirectAttributes) {
		
		if(bidingResult.hasErrors()) {
			//入力エラーがあれば登録画面に遷移
			return "pc/register";
		}
		
		pcService.register(request);
		
		redirectAttributes.addFlashAttribute("message", "登録が完了しました");
		return "redirect:/pcs";//一覧画面へリダイレクト
	}
}
