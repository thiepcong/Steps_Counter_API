package com.example.stepcounter.exceptions;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.stepcounter.common.ResponseApi;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	// Xử lý mọi exception 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseApi> unhandledExceptions(Exception ex){
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		
		ex.printStackTrace();
		
		return new ResponseEntity<ResponseApi>(new ResponseApi(false, message), HttpStatus.NOT_FOUND);
	}
	
}
