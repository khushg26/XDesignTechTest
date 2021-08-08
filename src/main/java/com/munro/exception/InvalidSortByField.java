package com.munro.exception;

public class InvalidSortByField extends RuntimeException {
	private String message;

	public InvalidSortByField(String message) {
		this.message = message;
	}

	public InvalidSortByField(String message, Exception e) {
		super(e);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
