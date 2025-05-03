package com.flipkart.groceries.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

	int statusCode;
	String errorMessage;
	
	
	public ErrorResponse(int statusCode, String errorMessage) {
		this.errorMessage=errorMessage;
		this.statusCode = statusCode;
	}

}
