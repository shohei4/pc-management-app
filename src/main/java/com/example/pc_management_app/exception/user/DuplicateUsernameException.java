package com.example.pc_management_app.exception.user;

import com.example.pc_management_app.exception.base.ConflictException;

/**
 * ユーザー名競合の例外クラス
 */
public class DuplicateUsernameException extends ConflictException{
	
	private final String username;
	
	public DuplicateUsernameException(String username) {
		// TODO 自動生成されたコンストラクター・スタブ
		super("ユーザー名はすでに登録されています：" + username);
		this.username = username;
	}

	@Override
	public String getErrorCode() {
		// TODO 自動生成されたメソッド・スタブ
		return "DUPLICATE_USERNAME";
	}
	
	public String getUsername() {
		return username;
	}
	
}
