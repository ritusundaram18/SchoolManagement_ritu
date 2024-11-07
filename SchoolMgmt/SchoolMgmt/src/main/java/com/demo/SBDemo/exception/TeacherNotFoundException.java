package com.demo.SBDemo.exception;

public class TeacherNotFoundException extends RuntimeException {
	
	public TeacherNotFoundException() {
	}
	public TeacherNotFoundException(String exceptionMsg) {
		super(exceptionMsg);
	}

}
