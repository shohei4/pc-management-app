package com.example.pc_management_app.exception.user;

import com.example.pc_management_app.exception.base.NotFoundException;

public class UserNotFoundException extends NotFoundException{

	protected UserNotFoundException(Long id) {
		super("ユーザーが見つかりません： id=" + id);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public String getErrorCode() {
		// TODO 自動生成されたメソッド・スタブ
		return "USER_NOT_FOUND";
	}

}
