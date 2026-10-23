package com.example.pc_management_app.exception.base;

import org.springframework.http.HttpStatus;

/**
 * 例外の分類とステータス対応の基底クラス
 */
public abstract class ApplicationException extends RuntimeException {
	protected ApplicationException(String message) {
		super(message);
	}

	protected ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getErrorCode() {
		return "RESOURCE_NOT_FOUND";
	}

	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
