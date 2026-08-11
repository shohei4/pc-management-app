package com.example.pc_management_app.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateUsernameException.class)
    public String handleDuplicateUsername(DuplicateUsernameException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "register"; // 例外発生時に自動的にこの画面に戻る
    }
}
