package com.example.pc_management_app.exception.global;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.pc_management_app.exception.base.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	/**
	 * リソースが見つからない時のハンドラメソッド
	 * @param e
	 * @param model
	 * @return エラーページ404
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handlePcNotFound(ResourceNotFoundException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "error/404";
	}
	
	/**
	 * 予期しないエラーをハンドラメソッド
	 * @param e
	 * @param model
	 * @return エラーページ500
	 */
	@ExceptionHandler(Exception.class)
	public String handleUnexpected(Exception e, Model model) {
		log.error("予期しないエラー", e);
		model.addAttribute("message", "システムエラーが発生しました");
		return "error/500";
	}

}
