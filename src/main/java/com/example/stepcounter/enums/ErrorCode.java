package com.example.stepcounter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	ACCOUNT_NOT_FOUND("Tài khoản không tồn tại!");	
	private String message;
	
}