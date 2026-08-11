package com.example.pc_management_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterForm {
	@NotBlank
	private String username;

	@NotBlank
	@Size(min = 8, message = "8文字以上で入力してください")
	private String password;

	@NotBlank
	private String confirmPassword;
}
