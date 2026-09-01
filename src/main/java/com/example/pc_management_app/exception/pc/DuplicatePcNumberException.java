package com.example.pc_management_app.exception.pc;

import com.example.pc_management_app.exception.base.ConflictException;

import lombok.Getter;

@Getter
public class DuplicatePcNumberException extends ConflictException {
	
	private final String pcNumber;
	
	protected DuplicatePcNumberException(String pcNumber) {
		super("PCナンバーは既に登録されています：" + pcNumber);
		this.pcNumber = pcNumber;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public String getErrorCode() {
		// TODO 自動生成されたメソッド・スタブ
		return "DUPLICATE_PCNUMBER";
	}
	
}
