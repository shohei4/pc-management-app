package com.example.pc_management_app.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
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
	
	private Integer pcNumber;
	@NotBlank(message = "利用者名は必須です")
	@Size(max = 50, message="利用者名は50文字以内で入力してください")
	private String userName;
	
	@NotBlank(message = "利用者属性を選択してください")
	private String UserAttr;
	
	private List<Long> selectedSoftwareIds;
	
	private String maker;
	
	private String os;
	
	private String remarks;
}
