package com.example.pc_management_app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pc_management_app.dto.pc.PcListItemDto;
import com.example.pc_management_app.dto.pc.PcRequest;
import com.example.pc_management_app.exception.pc.DuplicatePcNumberException;
import com.example.pc_management_app.service.PcService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/pcs")
@RequiredArgsConstructor
public class PcController {

	private final PcService pcService;
	
	/**
	 * フォーム初期値をフロントにmodelに詰めるメソッド
	 * @param model
	 */
	private void addFormOptions(Model model) {
		model.addAttribute("userAttrOptions", List.of("利用者", "スタッフ", "体験者"));
		model.addAttribute("osOptions", List.of("Windows11", "Windows10"));
	}
	
	/**
	 * フォーム初期値とpcIdをmodelに詰めるメソッド
	 * @param model
	 * @param id　PCのID
	 */
	private void prepareUpdateForm(Model model, Long id) {
	    addFormOptions(model);
	    model.addAttribute("pcId", id);
	}
	
	private void addModelByUserAttr(Model model, Map<String, List<PcListItemDto>> grouped) {
		//キーごとにモデルに追加
		List<PcListItemDto> userPcList = grouped.getOrDefault("利用者", Collections.emptyList());
		List<PcListItemDto> staffPcList = grouped.getOrDefault("スタッフ", Collections.emptyList());
	    List<PcListItemDto> trialPcList = grouped.getOrDefault("体験者", Collections.emptyList());
		
	    model.addAttribute("staffPcList", staffPcList);
	    model.addAttribute("userPcList", userPcList);
	    model.addAttribute("trialPcList", trialPcList);
	}

	/**
	 * 一覧表示用のコントローラーメソッド
	 * @param model(staffPcList:スタッフ属性を持つPC情報,userPcList:利用者属性を持つPC情報,trialPcList:体験者属性を持つPC情報)
	 * @return 一覧画面URL
	 */
	@GetMapping
	public String list(Model model) {
		Map<String, List<PcListItemDto>> grouped = pcService.findAllPcForListGroupedByUserAttr();
		
		//キーごとにモデルに追加
		addModelByUserAttr(model, grouped);
		return "pc/list";
	}
	
	/**
	 * PCナンバーでの検索
	 * @param model
	 * @param pcNumber
	 * @return
	 */
	@GetMapping("/pcNumber/{pcNumber}")
	public String findBypcNumber(Model model, @PathVariable String pcNumber) {
		model.addAttribute(pcService.findByPcNumber(pcNumber));
		return "pc/list";
	}
	
	/**
	 * 登録画面表示用のコントローラーメソッド
	 * @param model(pcRequest:入力値を扱うDTO)
	 * @return　登録フォームURL
	 */
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		//フロント表示で使用するためにインスタンス化
		PcRequest pcRequest = new PcRequest();
	    pcRequest.setSoftwareNames(new ArrayList<>(List.of("Office"))); // 表示専用の初期値としてここで設定
	    model.addAttribute("pcRequest", pcRequest);
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
			model.addAttribute("pcRequest", request);
			//入力エラーがあれば登録画面に遷移
			return "pc/register";
		}
		
		try {
			pcService.register(request);
			redirectAttributes.addFlashAttribute("message", "登録が完了しました");
			return "redirect:/pcs";//一覧画面へリダイレクト
		}catch(DuplicatePcNumberException e) {
			model.addAttribute("pcRequest", request);
			model.addAttribute("errorMessage", e);
			return "pc/register";
		}
		
	}

	//更新フォーム表示
	@GetMapping("/update/{id}")
	public String showUpdateForm(Model model, @PathVariable Long id) {
		PcRequest pcRequest = pcService.findById(id);
		model.addAttribute("pcRequest", pcRequest);
		prepareUpdateForm(model, id);
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
			//プルダウンメニューの値、pcId受け渡し処理
			prepareUpdateForm(model, id);
			//入力エラーがあれば更新画面に遷移
			return "pc/update";
		}

		pcService.update(request, id);
		redirectAttributes.addFlashAttribute("message", "更新が完了しました");
		return "redirect:/pcs";//一覧画面へリダイレクト
	}
}
