package com.example.stepcounter.exceptions;

import com.example.stepcounter.enums.ErrorCode;

public class CommandException extends RuntimeException{
	public CommandException(ErrorCode errorCode) {
		super(errorCode.getMessage());
	}
}