package com.demo.SBDemo.dto;

//import com.demo.SBDemo.entity.Login;

import lombok.Data;

@Data
public class TeacherDTO {

	private int id;
	private String name;
	private String address;
	private LoginDTO loginDTO;

}
