package com.demo.SBDemo.dto;

import lombok.Data;

@Data
public class StudentDTO {
	private int id;
	private String name;
	private String address;
	private String imagePath;
	private LoginDTO loginDTO;
}
