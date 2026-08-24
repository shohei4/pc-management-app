package com.example.pc_management_app.exception.base;

import org.springframework.http.HttpStatus;

/**
 * データの競合を扱う基底例外クラス
 */
public abstract class ConflictException extends ApplicationException{
	
	protected ConflictException(String message) {
		super(message);
	}
	
	protected ConflictException(String message, Throwable cause) {
		super(message, cause);
	}
	
	@Override
	public HttpStatus getStatus() {
		return HttpStatus.CONFLICT;
	}

}
