package com.demo.SBDemo.exception;

public class UsernameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsernameNotFoundException() {
	}

	public UsernameNotFoundException(String exceptionMsg) {
		super(exceptionMsg);
	}

}
