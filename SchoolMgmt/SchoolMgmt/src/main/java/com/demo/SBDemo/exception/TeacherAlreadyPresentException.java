package com.demo.SBDemo.exception;

public class TeacherAlreadyPresentException extends RuntimeException {
	
	
	public TeacherAlreadyPresentException() {
	}

	public TeacherAlreadyPresentException(String exceptionMsg) {
		super(exceptionMsg);
	}

}
