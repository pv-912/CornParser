package com.main.cornParser.exception;

public class InvalidCronStringException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidCronStringException(String msg) {
		super(msg);
	}
	
}
