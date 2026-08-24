package com.example.pc_management_app.dto.error;

import lombok.Data;

@Data
public class ErrorResponse {
	private final String errorCode;
	private final String message;
}
