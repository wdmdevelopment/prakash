package com.wdm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductException(ProductNotFoundException exception){
		 
		 ErrorResponse errorResponse = new ErrorResponse();
		 errorResponse.setStatuserrorCode(HttpStatus.NOT_FOUND.value());
		 
		 errorResponse.setMessage(exception.getMessage());
		 errorResponse.setTimeStamp(System.currentTimeMillis());
		 return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ErrorResponse> handleProductException(Exception exception){
		
		
		 ErrorResponse errorResponse = new ErrorResponse();
		 errorResponse.setStatuserrorCode(HttpStatus.BAD_REQUEST.value());
		 
		 errorResponse.setMessage(exception.getMessage());
		 errorResponse.setTimeStamp(System.currentTimeMillis());
		 return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(OrderCustomException.class)
	
	 public ResponseEntity<ErrorResponse> handleCustomException(OrderCustomException exception) {
		
		
		
		ErrorResponse errorResponse = new ErrorResponse();
		 errorResponse.setStatuserrorCode(HttpStatus.NOT_FOUND.value());
		 
		 errorResponse.setMessage(exception.getMessage());
		 errorResponse.setTimeStamp(System.currentTimeMillis());
		 return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
