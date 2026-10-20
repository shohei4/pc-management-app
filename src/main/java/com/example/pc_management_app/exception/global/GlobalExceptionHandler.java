package com.example.pc_management_app.exception.global;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.pc_management_app.exception.pc.PcNotFoundException;
import com.example.pc_management_app.exception.user.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	/**
	 * ユーザー情報が見つからない時のハンドラメソッド
	 * @param e
	 * @param model
	 * @return エラーページ404
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public String handleUserNotFound(UserNotFoundException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "error/404";
	}
	
	/**
	 * PC情報が見つからない時のハンドラメソッド
	 * @param e
	 * @param model
	 * @return エラーページ404
	 */
	@ExceptionHandler(PcNotFoundException.class)
	public String handlePcNotFound(PcNotFoundException e, Model model) {
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
