package com.flipkart.groceries.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flipkart.groceries.dto.ErrorResponse;
import com.flipkart.groceries.exception.InventoryNotFound;
import com.flipkart.groceries.exception.ItemNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ ItemNotFound.class, InventoryNotFound.class })
	public ResponseEntity<ErrorResponse> notFoundExceptionHandler(Exception e) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}