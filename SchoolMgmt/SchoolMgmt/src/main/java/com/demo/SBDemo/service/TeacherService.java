package com.demo.SBDemo.service;


import java.util.List;

import com.demo.SBDemo.dto.StudentDTO;
//import com.demo.SBDemo.dto.StudentDTO;
import com.demo.SBDemo.dto.TeacherDTO;

public interface TeacherService {
	
	public TeacherDTO saveTeacher(TeacherDTO teacherDTO);
	
	public TeacherDTO fetchTeacher(int techId);
	
	public TeacherDTO updateTeacher(TeacherDTO teacherDTO);
	
	public List<TeacherDTO> fetchTeachers();
	
	

}
