package com.raduq.statistics.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.raduq.statistics.exception.TransactionTimeoutException;

@ControllerAdvice
public class TransactionAdvice {

	@ExceptionHandler(TransactionTimeoutException.class)
	public final ResponseEntity handleTransactionTimeout() {
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest().body( e.getBindingResult().getFieldError().getDefaultMessage() );
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public final ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		return ResponseEntity.badRequest().body( e.getMessage() );
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> defaultException(Exception e) {
		return new ResponseEntity<>( "Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR );
	}

}



