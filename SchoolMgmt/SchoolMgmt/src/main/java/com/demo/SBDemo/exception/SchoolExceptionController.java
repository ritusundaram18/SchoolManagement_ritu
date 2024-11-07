package com.demo.SBDemo.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.SBDemo.dto.SchoolResponse;

@ControllerAdvice
public class SchoolExceptionController {

	@ExceptionHandler(value = StudentNotFoundException.class)
	public ResponseEntity<SchoolResponse> studentNFE(StudentNotFoundException exception) {
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("false");
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setData(null);

		Map<String, String> error = new LinkedHashMap<>();
		error.put("errorMsg", exception.getMessage());
		response.setError(error);
		return new ResponseEntity<SchoolResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = StudentAlreadyPresentException.class)
	public ResponseEntity<SchoolResponse> studentAPE(StudentAlreadyPresentException exception) {
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("false");
		response.setStatusCode(HttpStatus.FOUND.value());
		response.setData(null);

		Map<String, String> error = new LinkedHashMap<>();
		error.put("errorMsg", exception.getMessage());
		response.setError(error);
		return new ResponseEntity<SchoolResponse>(response, HttpStatus.FOUND);
	}

	@ExceptionHandler(value = LoginException.class)
	public ResponseEntity<SchoolResponse> loginException(LoginException exception) {
		System.out.println("----exceptionhandler--starts----from loginException() method");
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("false");
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setData(null);

		Map<String, String> error = new LinkedHashMap<>();
		error.put("errorMsg", exception.getMessage());
		response.setError(error);
		System.out.println("----exceptionhandler--ends----from loginException() method");
		return new ResponseEntity<SchoolResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<SchoolResponse> invalidCredentialsException(InvalidCredentialsException exception) {
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("false");
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setData(null);

		Map<String, String> error = new LinkedHashMap<>();
		error.put("errorMsg", exception.getMessage());
		response.setError(error);
		return new ResponseEntity<SchoolResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public ResponseEntity<SchoolResponse> usernameNotFoundException(UsernameNotFoundException exception) {
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("false");
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setData(null);

		Map<String, String> error = new LinkedHashMap<>();
		error.put("errorMsg", exception.getMessage());
		response.setError(error);
		return new ResponseEntity<SchoolResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidAccessTokenException.class)
	public ResponseEntity<SchoolResponse> invalidAccessTokenException(InvalidAccessTokenException exception) {
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("false");
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setData(null);

		Map<String, String> error = new LinkedHashMap<>();
		error.put("errorMsg", exception.getMessage());
		response.setError(error);
		return new ResponseEntity<SchoolResponse>(response, HttpStatus.NOT_FOUND);
	}

}
