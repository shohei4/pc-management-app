package com.example.pc_management_app.dto.user;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegisterForm {
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "半角英数字のみで入力してください")
	private String username;

	@NotBlank
	@Size(min = 8, message = "8文字以上で入力してください")
	private String password;

	@NotBlank
	private String confirmPassword;

	// password一致バリデーション
	@AssertTrue(message = "パスワードが一致しません")
	public boolean isPasswordMatching() {
		return password != null && password.equals(confirmPassword);
	}

}
