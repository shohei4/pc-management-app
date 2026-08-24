package com.example.pc_management_app.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.pc_management_app.exception.user.DuplicateUsernameException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//ユーザー名の一意制約エラーを処理するハンドラメソッド
	@ExceptionHandler(DuplicateUsernameException.class)
    public String handleDuplicateUsername(DuplicateUsernameException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "user/register"; // 例外発生時に自動的にこの画面に戻る
    }
}
