package com.main.cornParser.exception;

public class CronParsingException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CronParsingException(String msg) {
		super(msg);
	}
	
}
