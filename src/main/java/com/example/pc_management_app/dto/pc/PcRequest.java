package com.example.pc_management_app.dto.pc;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PcRequest {
	
	@Pattern(regexp = "\\d{1,3}", message = "PC番号は3桁以内の数字で入力してください")
	private String pcNumber;
	@NotBlank(message = "利用者名は必須です")
	@Size(max = 50, message="利用者名は50文字以内で入力してください")
	private String userName;
	
	@NotBlank(message = "利用者属性を選択してください")
	private String userAttr;
	
	@Builder.Default
	private List<String> softwareNames = new ArrayList<>();
	
	private String maker;
	
	@Builder.Default
	private String os = "Windows11";
	
	private String remarks;
}
