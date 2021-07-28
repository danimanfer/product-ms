package com.example.product.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {

	
	    @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        List<ErrorObject> message = getErrors(ex);
	        ErrorResponse errorResponse = getErrorResponse(ex, status, message);
	        return new ResponseEntity<>(errorResponse, status);
	    }
	    


	    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorObject>  errors) {
	        return new ErrorResponse( status.value(),
	        		errors.get(0).getMessage());
	    }

	    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
	        return ex.getBindingResult().getFieldErrors().stream()
	                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
	                .collect(Collectors.toList());
	    }
}

