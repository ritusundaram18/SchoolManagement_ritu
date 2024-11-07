package com.demo.SBDemo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.SBDemo.dto.LoginDTO;
import com.demo.SBDemo.dto.StudentDTO;
import com.demo.SBDemo.encryption.EncryptionUtilityService;
//import com.demo.SBDemo.encryption.EncyptionUtilityService;
import com.demo.SBDemo.entity.Login;
import com.demo.SBDemo.entity.Student;
import com.demo.SBDemo.exception.StudentAlreadyPresentException;
import com.demo.SBDemo.exception.StudentNotFoundException;
import com.demo.SBDemo.repository.LoginRepository;
import com.demo.SBDemo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EncryptionUtilityService encyptionUtilityService;

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public StudentDTO saveStudent(StudentDTO studentDTO) {
		Optional<Student> opStudent = studentRepository.findById(studentDTO.getId());
		if (opStudent.isPresent()) {
			throw new StudentAlreadyPresentException(
					"Student with id " + studentDTO.getId() + " is aready present , Please generate new Id");
		}

		System.out.println("----StudentDTO-----" + studentDTO.toString());
		logger.info("----StudentDTO-----" + studentDTO.toString());
		Student student = new Student();
		// student.setId(studentDTO.getId());
		student.setName(studentDTO.getName());
		student.setAddress(studentDTO.getAddress());

		File file = new File(studentDTO.getImagePath());
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte imageInByteArray[] = IOUtils.toByteArray(inputStream);
			student.setImage(imageInByteArray);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Login login = new Login();
		// login.setId(studentDTO.getLoginDTO().getId());
		login.setUsername(studentDTO.getLoginDTO().getUsername());

		// login.setPassword(studentDTO.getLoginDTO().getPassword());

		login.setPassword(encyptionUtilityService.passwordEncoder().encode(studentDTO.getLoginDTO().getPassword()));

		login.setLoginType(studentDTO.getLoginDTO().getLoginType());

		student.setLogin(login);

		Student dbStudent = studentRepository.save(student);
		StudentDTO responseStudentDTO = new StudentDTO();
		responseStudentDTO.setId(dbStudent.getId());
		responseStudentDTO.setName(dbStudent.getName());
		responseStudentDTO.setAddress(dbStudent.getAddress());

		LoginDTO responseLoginDTO = new LoginDTO();
		responseLoginDTO.setId(dbStudent.getLogin().getId());
		responseLoginDTO.setUsername(dbStudent.getLogin().getUsername());
		responseLoginDTO.setPassword(dbStudent.getLogin().getPassword());
		responseLoginDTO.setLoginType(dbStudent.getLogin().getLoginType());

		responseStudentDTO.setLoginDTO(responseLoginDTO);

		return responseStudentDTO;
	}

	@Override
	public StudentDTO fetchStudent(int stuId) {
		// Student student = studentRepository.getById(stuId);
		// getById() is designed to throw exception in case of Entity is not found
		// but we needed Null in case of missing Entity
		// if (student == null) {
		// return null;
		// }

		Optional<Student> optionalStudent = studentRepository.findById(stuId);
		if (!optionalStudent.isPresent()) {
			throw new StudentNotFoundException("Student with Id " + stuId + " not found");
		}

		Student student = optionalStudent.get();

		System.out.println("---------student-------" + student);
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setAddress(student.getAddress());
		return studentDTO;
	}

	@Override
	public void removeStudent(int stuId) {
		Optional<Student> optionalStudent = studentRepository.findById(stuId);
		if (!optionalStudent.isPresent()) {
			throw new StudentNotFoundException("Student with Id " + stuId + " not found");
		}
		studentRepository.deleteById(stuId);
	}

	@Override
	public StudentDTO updateStudent(StudentDTO studentDTO) {
		System.out.println("student DTO------for update----" + studentDTO);
		Student student = studentRepository.getById(studentDTO.getId());
		StudentDTO responseStudentDTO = null;
		if (student != null) {
			System.out.println("---------student-------" + student);
			student.setName(studentDTO.getName());
			student.setAddress(studentDTO.getAddress());
			Student dbStudent = studentRepository.save(student);

			responseStudentDTO = new StudentDTO();
			responseStudentDTO.setId(dbStudent.getId());
			responseStudentDTO.setName(dbStudent.getName());
			responseStudentDTO.setAddress(dbStudent.getAddress());

		}
		return responseStudentDTO;

		// studentRepository.save() //can be used for both purpose (Insert/Update)
	}

	@Override
	public List<StudentDTO> fetchStudents() {
		List<Student> students = studentRepository.findAll();
		System.out.println("----students----" + students);
		List<StudentDTO> studentDTOs = new ArrayList<>();
		for (Student student : students) {
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setId(student.getId());
			studentDTO.setName(student.getName());
			studentDTO.setAddress(student.getAddress());
			studentDTOs.add(studentDTO);
		}
		return studentDTOs;
	}

	@Override
	public StudentDTO fetchStudent(String stuName) {
		System.out.println("+---------name----" + stuName);

		// fetch student record by using student name
		Student student = studentRepository.findByName(stuName);

		// map the student to studentDTO class for controller
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setAddress(student.getAddress());

		return studentDTO;
	}

}
