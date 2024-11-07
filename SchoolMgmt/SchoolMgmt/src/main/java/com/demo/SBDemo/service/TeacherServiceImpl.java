package com.demo.SBDemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.SBDemo.dto.LoginDTO;
import com.demo.SBDemo.dto.StudentDTO;
import com.demo.SBDemo.dto.TeacherDTO;
import com.demo.SBDemo.encryption.EncryptionUtilityService;
import com.demo.SBDemo.entity.Login;
//import com.demo.SBDemo.entity.Student;
import com.demo.SBDemo.entity.Teacher;
import com.demo.SBDemo.exception.TeacherAlreadyPresentException;
//import com.demo.SBDemo.exception.StudentAlreadyPresentException;
//import com.demo.SBDemo.exception.StudentNotFoundException;
import com.demo.SBDemo.exception.TeacherNotFoundException;
//import com.demo.SBDemo.repository.StudentRepository;
import com.demo.SBDemo.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private EncryptionUtilityService encyptionUtilityService; 
	
	@Override
	public TeacherDTO fetchTeacher(int techId) {

		Teacher teacher = teacherRepository.getById(techId);
		// getById() is designed to throw exception in case of Entity is not found
		// but we needed Null in case of missing Entity
//				 if (teacher == null)  return null;
//				 
//				 

		Optional<Teacher> optionalTeacher = teacherRepository.findById(techId);
		if (!optionalTeacher.isPresent()) {
			throw new TeacherNotFoundException("Teacher with Id " + techId + " not found");
		}

		// HERE I WILL ADD optional CLASS CODE , WHEN I WILL HANDLE THE EXCEPTION

		System.out.println("---------Teacher-------" + teacher);
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(teacher.getId());
		teacherDTO.setName(teacher.getName());
		teacherDTO.setAddress(teacher.getAddress());
		return teacherDTO;
	}

	@Override
	public TeacherDTO saveTeacher(TeacherDTO teacherDTO) {

		Optional<Teacher> opTeacher = teacherRepository.findById(teacherDTO.getId());
		if (opTeacher.isPresent()) {
			throw new TeacherAlreadyPresentException(
					"Teacher with id " + teacherDTO.getId() + " is aready present , Please generate new Id");
		}

//		System.out.println("----StudentDTO-----" + studentDTO.toString());

		System.out.println("--- TeacherDTO-----" + teacherDTO.toString());
		Teacher teacher = new Teacher();
		teacher.setId(teacherDTO.getId());
		teacher.setName(teacherDTO.getName());
		teacher.setAddress(teacherDTO.getAddress());

		Login login = new Login();
		login.setId(teacherDTO.getLoginDTO().getId());
		login.setUsername(teacherDTO.getLoginDTO().getUsername());
//		login.setPassword(teacherDTO.getLoginDTO().getPassword()); //saving plain text pwd
		
		login.setPassword(encyptionUtilityService.passwordEncoder().encode(teacherDTO.getLoginDTO().getPassword())); //savind hashed pwd
		login.setLoginType(teacherDTO.getLoginDTO().getLoginType());

		teacher.setLogin(login);

		Teacher dbTeacher = teacherRepository.save(teacher);
		TeacherDTO responseTeacherDTO = new TeacherDTO();
		responseTeacherDTO.setId(dbTeacher.getId());
		responseTeacherDTO.setName(dbTeacher.getName());
		responseTeacherDTO.setAddress(dbTeacher.getAddress());

		LoginDTO responseLoginDTO = new LoginDTO();
		responseLoginDTO.setId(dbTeacher.getLogin().getId());
		responseLoginDTO.setUsername(dbTeacher.getLogin().getUsername());
		responseLoginDTO.setPassword(dbTeacher.getLogin().getPassword());
		responseLoginDTO.setLoginType(dbTeacher.getLogin().getLoginType());

//		responseTeacherDTO.setLoginDTO(responseLoginDTO);

		return responseTeacherDTO;
	}

//return teacherDTO;

	// }

	@Override
	public TeacherDTO updateTeacher(TeacherDTO teacherDTO) {

		System.out.println("student DTO------for update----" + teacherDTO);
		Teacher teacher = teacherRepository.getById(teacherDTO.getId());
		TeacherDTO responseTeacherDTO = null;
		if (teacher != null) {
			System.out.println("---------student-------" + teacher);
			teacher.setName(teacherDTO.getName());
			teacher.setAddress(teacherDTO.getAddress());
			Teacher dbTeacher = teacherRepository.save(teacher);

			responseTeacherDTO = new TeacherDTO();
			responseTeacherDTO.setId(dbTeacher.getId());
			responseTeacherDTO.setName(dbTeacher.getName());
			responseTeacherDTO.setAddress(dbTeacher.getAddress());

		}
		return responseTeacherDTO;

	}

	@Override
	public List<TeacherDTO> fetchTeachers() {

		List<Teacher> teachers = teacherRepository.findAll();
		System.out.println("----students----" + teachers);
		List<TeacherDTO> teacherDTOs = new ArrayList<>();
		for (Teacher teacher : teachers) {
			TeacherDTO teacherDTO = new TeacherDTO();
			teacherDTO.setId(teacher.getId());
			teacherDTO.setName(teacher.getName());
			teacherDTO.setAddress(teacher.getAddress());
			teacherDTOs.add(teacherDTO);
		}
		return teacherDTOs;

	}

}
