package com.demo.SBDemo.exception;

public class StudentAlreadyPresentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StudentAlreadyPresentException() {
	}

	public StudentAlreadyPresentException(String exceptionMsg) {
		super(exceptionMsg);
	}

}
