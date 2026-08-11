package com.example.pc_management_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pc_management_app.dto.UserRegisterForm;
import com.example.pc_management_app.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserContorller {
	private final UserService userService;

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("userRegisterForm", new UserRegisterForm());
		return "user/register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute UserRegisterForm form, BindingResult result, Model model) {
		if (!form.getPassword().equals(form.getConfirmPassword())) {
			model.addAttribute("errorMessage", "パスワードが一致しません");
			return "user/register";
		}
		if (result.hasErrors()) {
			return "user/register";
		}

		userService.save(form);
		return "redirect:/login?registered";
	}
}
