package com.munro.exception;

public class InvalidHillCategoryException extends RuntimeException {
	private String message;

	public InvalidHillCategoryException(String message) {
		this.message = message;
	}

	public InvalidHillCategoryException(String message, Exception e) {
		super(e);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}