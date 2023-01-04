package com.wdm.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(SuperMarketIdNotFoundException.class)
	
	
	public ResponseEntity<Object> handleExceptions(SuperMarketIdNotFoundException exception) {
	
        ExceptionResponse response = new ExceptionResponse();
	
        	response.setMessage("Supermarket Not found");
	
  ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	
        return entity;
    }
	
	
@ExceptionHandler(ProductIdNotFoundException.class)
	
	
	public ResponseEntity<Object> handleExceptionProduct( ProductIdNotFoundException exception) {
	
        ExceptionResponse response = new ExceptionResponse();
	
        	response.setMessage("Product Not found");
	
  ResponseEntity<Object> res = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	
        return res;
    }
	

}
