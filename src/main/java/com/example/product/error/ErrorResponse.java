package com.example.product.error;

public class ErrorResponse {
	
	
    private final int status_code;
    private final String message;


	public ErrorResponse(int status_code, String message) {
		super();
		this.status_code = status_code;
		this.message = message;
	}


	public int getStatus_code() {
		return status_code;
	}


	public String getMessage() {
		
		
		return message;
	}
    
    

}
