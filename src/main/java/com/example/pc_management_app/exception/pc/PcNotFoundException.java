package com.example.pc_management_app.exception.pc;

import com.example.pc_management_app.exception.base.NotFoundException;

public class PcNotFoundException extends NotFoundException{

	public PcNotFoundException(Long id) {
		super("PC情報が見つかりません： id=" + id);
	}

	@Override
	public String getErrorCode() {
		// TODO 自動生成されたメソッド・スタブ
		return "PC_NOT_FOUND";
	}

}
