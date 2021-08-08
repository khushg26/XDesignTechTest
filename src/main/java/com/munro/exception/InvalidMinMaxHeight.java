package com.munro.exception;

public class InvalidMinMaxHeight extends RuntimeException {
	private String message;

	public InvalidMinMaxHeight(String message) {
		this.message = message;
	}

	public InvalidMinMaxHeight(String message, Exception e) {
		super(e);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
