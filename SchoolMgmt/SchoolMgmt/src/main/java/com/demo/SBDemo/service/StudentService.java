package com.demo.SBDemo.service;

import java.util.List;

import com.demo.SBDemo.dto.StudentDTO;

public interface StudentService {

	public StudentDTO saveStudent(StudentDTO studentDTO);

	public StudentDTO fetchStudent(int stuId);

	public void removeStudent(int stuId);

	public StudentDTO updateStudent(StudentDTO studentDTO);

	public List<StudentDTO> fetchStudents();

	public StudentDTO fetchStudent(String stuName);

}
