package com.example.pc_management_app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pc_management_app.dto.PcListItemDto;
import com.example.pc_management_app.dto.PcRequest;
import com.example.pc_management_app.service.PcService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/pcs")
@RequiredArgsConstructor
public class PcController {

	private final PcService pcService;

	private void addFormOptions(Model model) {
		model.addAttribute("userAttrOptions", List.of("利用者", "スタッフ", "体験者"));
		model.addAttribute("osOptions", List.of("Windows11", "Windows10"));
	}

	//一覧表示
	@GetMapping
	public String list(Model model) {
		Map<String, List<PcListItemDto>> grouped = pcService.findAllPcForListGroupedByUserAttr();
		
		//キーごとにモデルに追加
		List<PcListItemDto> userPcList = grouped.getOrDefault("利用者", Collections.emptyList());
		List<PcListItemDto> staffPcList = grouped.getOrDefault("スタッフ", Collections.emptyList());
	    List<PcListItemDto> trialPcList = grouped.getOrDefault("体験者", Collections.emptyList());
		
	    model.addAttribute("staffPcList", staffPcList);
	    model.addAttribute("userPcList", userPcList);
	    model.addAttribute("trialPcList", trialPcList);

		return "pc/list";
	}

	//登録フォーム表示
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("pcRequest", new PcRequest());
		//プルダウンメニューの値受け渡し処理
		addFormOptions(model);
		return "pc/register";
	}

	//登録処理
	@PostMapping("/register")
	public String save(@Valid @ModelAttribute PcRequest request,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			//プルダウンメニューの値受け渡し処理
			addFormOptions(model);
			//入力エラーがあれば登録画面に遷移
			return "pc/register";
		}

		pcService.register(request);
		redirectAttributes.addFlashAttribute("message", "登録が完了しました");
		return "redirect:/pcs";//一覧画面へリダイレクト
	}

	//更新フォーム表示
	@GetMapping("/update/{id}")
	public String showUpdateForm(Model model) {
		model.addAttribute("pcRequest", new PcRequest());
		//プルダウンメニューの値受け渡し処理
		addFormOptions(model);
		return "pc/update";
	}

	//更新処理
	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute PcRequest request,
			BindingResult bindingResult,
			@PathVariable Long id,
			Model model,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			//プルダウンメニューの値受け渡し処理
			addFormOptions(model);
			//入力エラーがあれば更新画面に遷移
			return "pc/update";
		}

		pcService.update(request, id);
		redirectAttributes.addFlashAttribute("message", "更新が完了しました");
		return "redirect:/pcs";//一覧画面へリダイレクト
	}
}
