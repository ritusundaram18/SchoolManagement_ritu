package com.demo.SBDemo.exception;

public class InvalidCredentialsException extends RuntimeException {
	
	public InvalidCredentialsException() {
	}
	public InvalidCredentialsException(String exceptionMsg) {
		super(exceptionMsg);
	}

}
