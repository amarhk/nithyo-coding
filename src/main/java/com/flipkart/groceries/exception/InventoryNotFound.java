package com.flipkart.groceries.exception;

public class InventoryNotFound extends Exception{

	private static final long serialVersionUID = 1L;

	public InventoryNotFound(String message) {
		super(message);
	}
}
