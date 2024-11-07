package com.demo.SBDemo.controller;

import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.SBDemo.dto.LoginDTO;
import com.demo.SBDemo.dto.SchoolResponse;
import com.demo.SBDemo.dto.StudentDTO;
import com.demo.SBDemo.dto.TeacherDTO;
import com.demo.SBDemo.service.LoginService;
import com.demo.SBDemo.utils.AppConstants;

@RestController
@RequestMapping(path = AppConstants.APP_ROOT_API)
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private HttpServletRequest request;

	@PostMapping(AppConstants.LOGIN_ROOT_API)
	public ResponseEntity<SchoolResponse> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request)
			throws UnknownHostException, URISyntaxException {
	
		Object resObject = loginService.login(loginDTO, request);
		HttpHeaders headers = new HttpHeaders();
		if (resObject instanceof StudentDTO) {
			StudentDTO res = (StudentDTO) resObject;
			headers.set("accessToken", res.getLoginDTO().getAccessToken());
		} else {
			TeacherDTO res = (TeacherDTO) resObject;
			headers.set("accessToken", res.getLoginDTO().getAccessToken());
		}
		SchoolResponse response = new SchoolResponse();
		response.setSuccess("true");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(resObject);
		response.setError(null);

		return ResponseEntity.ok().headers(headers).body(response);
	}

	@GetMapping(path = "/getReq")
	public String getRequest() {
		return "inside getRequest() method !!!";
	}

}