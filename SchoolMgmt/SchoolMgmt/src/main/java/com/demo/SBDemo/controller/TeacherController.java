package com.demo.SBDemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.SBDemo.dto.SchoolResponse;
import com.demo.SBDemo.dto.TeacherDTO;
import com.demo.SBDemo.service.LoginService;
//import com.demo.SBDemo.service.StudentService;
import com.demo.SBDemo.service.TeacherService;
import com.demo.SBDemo.utils.AppConstants;

@RestController
@RequestMapping(path = AppConstants.APP_ROOT_API)
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private LoginService loginService;

	// create end points for teachers

	// SAVE STUDENTS
	// HOME WORK
	@PostMapping(AppConstants.TEACHER_ROOT_API + AppConstants.ROOT_API_LOGGED_USERNAME)
	public SchoolResponse saveStudent(@RequestBody TeacherDTO teacherDTO,
			@PathVariable("loggedInUsername") String loggedInUsername, HttpServletRequest request) {
		SchoolResponse response = new SchoolResponse();
		String accessToken = request.getHeader("accessToken");
		// verifying the user
		boolean isValidUser = loginService.isValidUser(loggedInUsername, accessToken);

		if (isValidUser) {
			TeacherDTO studentDTOs = teacherService.saveTeacher(teacherDTO);
			response.setSuccess("true");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(studentDTOs);
			response.setError(null);
		}
		return response;
	}

	// get teacher
	// => /teachers/teachId/loggedInUsername
	@GetMapping(AppConstants.TEACHER_ROOT_API + AppConstants.TEACHER_ROOT_API_TECH_ID
			+ AppConstants.ROOT_API_LOGGED_USERNAME)
	public SchoolResponse getStudent(@PathVariable String teachId,
			@PathVariable("loggedInUsername") String loggedInUsername, HttpServletRequest request) {
		// fetching access token from header in request
		String accessToken = request.getHeader("accessToken");
		// verifying the user with access token
		boolean isValidUser = loginService.isValidUser(loggedInUsername, accessToken);
		SchoolResponse response = new SchoolResponse();
		if (isValidUser) {
			TeacherDTO teacherDTO = teacherService.fetchTeacher(Integer.valueOf(teachId));
			response.setSuccess("true");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(teacherDTO);
			response.setError(null);
		}
		return response;
	}

	// update teacher

	@PutMapping(AppConstants.TEACHER_ROOT_API)
	public SchoolResponse updateStudent(@RequestBody TeacherDTO teacherDTO) {
		TeacherDTO responseTeacherDTO = teacherService.updateTeacher(teacherDTO);
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("true");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(responseTeacherDTO);
		response.setError(null);
		return response;
	}

	// GET ALL Teacher
	@GetMapping(AppConstants.TEACHER_ROOT_API + AppConstants.ROOT_API_LOGGED_USERNAME)
	public SchoolResponse fetchTeachers(@PathVariable("loggedInUsername") String loggedInUsername,
			HttpServletRequest request) {
		// fetching access token from header in request
		String accessToken = request.getHeader("accessToken");
		// verifying the user with access token
		boolean isValidUser = loginService.isValidUser(loggedInUsername, accessToken);
		SchoolResponse response = new SchoolResponse();
		if (isValidUser) {
			List<TeacherDTO> teacherDTOs = teacherService.fetchTeachers();
			response.setSuccess("true");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(teacherDTOs);
			response.setError(null);
		}
		return response;
	}

}

// CREATE EXCEPTION , TEACHET NOT FOUND , TEACHER ALREADY PRESENT
