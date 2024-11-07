package com.demo.SBDemo.dto;

import lombok.Data;

@Data
public class LoginDTO {
	private int id;
	private String username;
	private String password;
	private String loginType;
	private String accessToken;

}