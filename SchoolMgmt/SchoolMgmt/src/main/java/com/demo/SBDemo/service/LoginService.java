package com.demo.SBDemo.service;

import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.demo.SBDemo.dto.LoginDTO;

public interface LoginService {

	public Object login(LoginDTO loginDTO, HttpServletRequest request) throws UnknownHostException, URISyntaxException;

	public boolean isValidUser(String loggedInUsername, String accessToken);
}
