package com.github.biryani.web.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	class ExceptionResponse {

	    private String message;
	    private LocalDateTime dateTime;
	    
	    public String getMessage() {
	        return message;
	    }
	    public void setMessage(String message) {
	        this.message = message;
	    }
	    public LocalDateTime getDateTime() {
	        return dateTime;
	    }
	    public void setDateTime(LocalDateTime dateTime) {
	        this.dateTime = dateTime;
	    }    
	}
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<Object> handleExceptions(IllegalArgumentException exception, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Not found");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
		
	}
	
	
	protected ResponseEntity<Object> handleIllegalArgumentException(
			IllegalArgumentException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

}
