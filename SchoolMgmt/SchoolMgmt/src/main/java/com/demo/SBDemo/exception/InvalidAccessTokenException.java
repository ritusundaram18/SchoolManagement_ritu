package com.demo.SBDemo.exception;

public class InvalidAccessTokenException extends RuntimeException {
	
	public InvalidAccessTokenException() {
	}
	public InvalidAccessTokenException(String exceptionMsg) {
		super(exceptionMsg);
	}

}
