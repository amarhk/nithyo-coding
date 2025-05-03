package com.flipkart.groceries.exception;

public class ItemNotFound extends Exception{

	private static final long serialVersionUID = 1L;

	public ItemNotFound(String message) {
		super(message);
	}
}
