package com.example.pc_management_app.exception.base;

import org.springframework.http.HttpStatus;

/**
 * データが見つからない時の基底例外クラス
 */
public abstract class NotFoundException extends ApplicationException {
	
	protected NotFoundException(String message) {
		super(message);
	}
	
	protected NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	@Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
