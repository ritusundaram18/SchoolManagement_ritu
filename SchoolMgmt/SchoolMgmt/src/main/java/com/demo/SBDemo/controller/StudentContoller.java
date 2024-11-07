package com.demo.SBDemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.SBDemo.dto.SchoolResponse;
import com.demo.SBDemo.dto.StudentDTO;
import com.demo.SBDemo.service.LoginService;
import com.demo.SBDemo.service.StudentService;
import com.demo.SBDemo.utils.AppConstants;

@RestController
@RequestMapping(path = AppConstants.APP_ROOT_API)
public class StudentContoller {

	@Autowired
	private StudentService studentService;

	@Autowired
	private LoginService loginService;

	// => /api/v1/students
	@PostMapping(AppConstants.STUDENT_ROOT_API)
	public SchoolResponse saveStudent(@RequestBody StudentDTO studentDTO) {
		StudentDTO responseStudentDTO = studentService.saveStudent(studentDTO);
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("true");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(responseStudentDTO);
		response.setError(null);
		return response;
	}

	// => /api/v1/students/{stuId}
	@GetMapping(AppConstants.STUDENT_ROOT_API + AppConstants.STUDENT_ROOT_API_STU_ID
			+ AppConstants.ROOT_API_LOGGED_USERNAME)
	public SchoolResponse getStudent(@PathVariable Integer stuId, @PathVariable String loggedInUsername,
			HttpServletRequest request) {
		SchoolResponse response = new SchoolResponse();
		String accessToken = request.getHeader("accessToken");
		// verifying the user with access token
		boolean isValidUser = loginService.isValidUser(loggedInUsername, accessToken);

		if (isValidUser) {
			StudentDTO studentDTO = studentService.fetchStudent(Integer.valueOf(stuId));
			response.setSuccess("true");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(studentDTO);
			response.setError(null);
		}
		return response;
	}

	// => /api/v1/students/{stuId}
	@DeleteMapping(AppConstants.STUDENT_ROOT_API + "/" + AppConstants.STUDENT_ROOT_API_STU_ID)
	public SchoolResponse deleteStudent(@PathVariable String stuId) {
		studentService.removeStudent(Integer.parseInt(stuId));
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("true");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData("Student with Id " + stuId + " is removed ");
		response.setError(null);
		return response;
	}

	// => /api/v1/students
	@PutMapping(AppConstants.STUDENT_ROOT_API)
	public SchoolResponse updateStudent(@RequestBody StudentDTO studentDTO) {
		StudentDTO responseStudentDTO = studentService.updateStudent(studentDTO);
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("true");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(responseStudentDTO);
		response.setError(null);
		return response;
	}

	// => /api/v1/students //previous
	// => /api/v1/students/{loggedInUsername} //Updated
	@GetMapping(AppConstants.STUDENT_ROOT_API + AppConstants.ROOT_API_LOGGED_USERNAME)
	public SchoolResponse getStudents(@PathVariable String loggedInUsername, HttpServletRequest request) {
		// fetching access token from header in request
		String accessToken = request.getHeader("accessToken");
		// verifying the user with access token
		boolean isValidUser = loginService.isValidUser(loggedInUsername, accessToken);
		
		SchoolResponse response = new SchoolResponse();
		if (isValidUser) {
			List<StudentDTO> studentDTOs = studentService.fetchStudents();
			response.setSuccess("true");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(studentDTOs);
			response.setError(null);
		}
		return response;
	}

	// => /api/v1/students/byName/{stuName}
	@GetMapping(AppConstants.STUDENT_ROOT_API + "/" + AppConstants.STUDENT_ROOT_API_NAME_AND_STU_ID)
	public SchoolResponse getStudentByName(@PathVariable String stuName) {
		StudentDTO studentDTO = studentService.fetchStudent(stuName);

		SchoolResponse response = new SchoolResponse();
		response.setSuccess("true");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(studentDTO);
		response.setError(null);
		return response;
	}

}
