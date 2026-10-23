package com.example.pc_management_app.exception.base;

/**
 * データが見つからない時の汎用例外クラス
 */
public class ResourceNotFoundException extends ApplicationException {
	 public ResourceNotFoundException(String message) {
	        super(message);
	    }
}
