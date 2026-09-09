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

	@Pattern(regexp = "^$|\\d{1,3}", message = "PC番号は3桁以内の数字で入力してください")
	private String pcNumber;
	@NotBlank(message = "利用者名は必須です")
	@Size(max = 50, message = "利用者名は50文字以内で入力してください")
	private String userName;

	@NotBlank(message = "利用者属性を選択してください")
	private String userAttr;

	@Builder.Default
	private List<String> softwareNames = new ArrayList<>();

	private String maker;

	@Builder.Default
	private String os = "Windows11";

	private String remarks;
	
	public void setPcNumber(String pcNumber) {
		this.pcNumber = normalizeToHalfWidth(pcNumber);
	}
	
	//PCナンバー半角フォーマットメソッド
	private String normalizeToHalfWidth(String input) {
	    if (input == null) return null;   // ① nullガード
	    StringBuilder sb = new StringBuilder();

	    for (char c : input.toCharArray()) {   // ② 1文字ずつ処理
	        int digit = Character.digit(c, 10); // ③ 数字なら0〜9、数字でなければ-1

	        if (digit != -1) {
	            sb.append(Character.forDigit(digit, 10)); // ④ 半角数字に変換して追加
	        } else {
	            sb.append(c); // ⑤ 数字でなければそのまま追加
	        }
	    }
	    return sb.toString();
	}
	
	
}
