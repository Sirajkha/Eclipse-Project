package com.irctc_booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.irctc_booking.response.ErrorResponse;

@RestControllerAdvice
public class CommonException {

	@ExceptionHandler(InsufficientBanlanceException.class)
	public ResponseEntity<ErrorResponse>  insufficentBanlance(InsufficientBanlanceException banlanceException){
		
		ErrorResponse response = new ErrorResponse("BE - 120", banlanceException.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}
}
