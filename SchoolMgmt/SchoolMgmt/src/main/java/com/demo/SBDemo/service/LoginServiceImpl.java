package com.demo.SBDemo.service;

import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.demo.SBDemo.dto.LoginDTO;
import com.demo.SBDemo.dto.StudentDTO;
import com.demo.SBDemo.dto.TeacherDTO;
import com.demo.SBDemo.encryption.EncryptionUtilityService;
import com.demo.SBDemo.entity.Login;
import com.demo.SBDemo.entity.Student;
import com.demo.SBDemo.entity.Teacher;
import com.demo.SBDemo.exception.InvalidAccessTokenException;
import com.demo.SBDemo.exception.InvalidCredentialsException;
import com.demo.SBDemo.exception.LoginException;
import com.demo.SBDemo.exception.UsernameNotFoundException;
import com.demo.SBDemo.repository.LoginRepository;
import com.demo.SBDemo.repository.StudentRepository;
import com.demo.SBDemo.repository.TeacherRepository;
import com.demo.SBDemo.utils.AppConstants;
import com.demo.SBDemo.utils.SchoolMgmtUtils;
import com.demo.SBDemo.utils.TokenResponse;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private EncryptionUtilityService encyptionUtilityService;

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Object login(LoginDTO loginDTO, HttpServletRequest request) throws UnknownHostException, URISyntaxException {
		// Business logic for login goes here

		Optional<Login> opLogin = loginRepository.findByUsernameAndLoginType(loginDTO.getUsername(),
				loginDTO.getLoginType());
		if (!opLogin.isPresent()) {
			throw new UsernameNotFoundException(AppConstants.WRONG_USERNAME_PASSWORD);
		}
		Login dbLogin = opLogin.get();
		boolean isValidPwd = encyptionUtilityService.passwordEncoder().matches(loginDTO.getPassword(),
				dbLogin.getPassword());

		if (!isValidPwd) {
			throw new InvalidCredentialsException(AppConstants.WRONG_USERNAME_PASSWORD);
		}

		StudentDTO responseStudentDTO = null;
		TeacherDTO responseTeacherDTO = null;
		if (loginDTO.getLoginType().equalsIgnoreCase("student")) {
			Student dbStudent = studentRepository.fetchStudentUsingCredentials(loginDTO.getUsername(),
					loginDTO.getLoginType());
			if (dbStudent == null) {
				throw new LoginException(AppConstants.WRONG_USERNAME_PASSWORD);
			}

			TokenResponse response = SchoolMgmtUtils.getAccessToken(request);
			if (response != null && response.getAccess_token() != null && response.getAccess_token() != "") {
				Login login = dbStudent.getLogin();
				login.setAccessToken(response.getAccess_token());
				loginRepository.save(login);
				System.out.println("---------" + response.getAccess_token());
			}
			LoginDTO resLoginDTO = new LoginDTO();
			resLoginDTO.setId(dbStudent.getLogin().getId());
			resLoginDTO.setUsername(dbStudent.getLogin().getUsername());
			resLoginDTO.setLoginType(dbStudent.getLogin().getLoginType());
			// resLoginDTO.setPassword(dbStudent.getLogin().getPassword());
			resLoginDTO.setAccessToken(response.getAccess_token());

			responseStudentDTO = new StudentDTO();
			responseStudentDTO.setId(dbStudent.getId());
			responseStudentDTO.setName(dbStudent.getName());
			responseStudentDTO.setAddress(dbStudent.getAddress());
			responseStudentDTO.setLoginDTO(resLoginDTO);
		} else {
			Teacher dbTeacher = teacherRepository.fetchTeacherUsingCredentials(loginDTO.getUsername(),
					loginDTO.getLoginType());

			if (dbTeacher == null) {
				throw new LoginException(AppConstants.WRONG_USERNAME_PASSWORD);
			}

			TokenResponse response = SchoolMgmtUtils.getAccessToken(request);
			if (response != null && response.getAccess_token() != null && response.getAccess_token() != "") {
				Login login = dbTeacher.getLogin();
				login.setAccessToken(response.getAccess_token());
				loginRepository.save(login);
				System.out.println("---------" + response.getAccess_token());
			}

			LoginDTO resLoginDTO = new LoginDTO();
			resLoginDTO.setId(dbTeacher.getLogin().getId());
			resLoginDTO.setUsername(dbTeacher.getLogin().getUsername());
			resLoginDTO.setLoginType(dbTeacher.getLogin().getLoginType());
			// resLoginDTO.setPassword(dbTeacher.getLogin().getPassword());
			resLoginDTO.setAccessToken(response.getAccess_token());

			responseTeacherDTO = new TeacherDTO();
			responseTeacherDTO.setId(dbTeacher.getId());
			responseTeacherDTO.setName(dbTeacher.getName());
			responseTeacherDTO.setAddress(dbTeacher.getAddress());
			responseTeacherDTO.setLoginDTO(resLoginDTO);

		}

		if (responseStudentDTO == null) {
			return responseTeacherDTO;
		} else {
			return responseStudentDTO;
		}
	}

	@Override
	public boolean isValidUser(String loggedInUsername, String reqAccessToken) {
		Optional<Login> opLogin = loginRepository.findByUsername(loggedInUsername);
		if (!opLogin.isPresent()) {
			throw new UsernameNotFoundException(AppConstants.USERNAME_NOT_FOUND);
		}
		Login login = opLogin.get();
		if (!ObjectUtils.isEmpty(login.getAccessToken())) {
			if (login.getAccessToken().equalsIgnoreCase(reqAccessToken)) {
				return true;
			} else {
				throw new InvalidAccessTokenException(AppConstants.ACCESS_TOKEN_MISMATCH);
			}
		} else {
			throw new InvalidAccessTokenException(AppConstants.ACCESS_TOKEN_MISMATCH);
		}
	}

}
