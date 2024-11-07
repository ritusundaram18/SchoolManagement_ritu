package com.demo.SBDemo.utils;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

public class SchoolMgmtUtils {

	private SchoolMgmtUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static TokenResponse getAccessToken(HttpServletRequest request)
			throws URISyntaxException, UnknownHostException {

		InetAddress ip;
		ResponseEntity<TokenResponse> response = null;
		RestTemplate restTemplate = new RestTemplate();

		//authenticating the user by passing client ID & Secret
		restTemplate.getInterceptors()
				.add(new BasicAuthenticationInterceptor(AppConstants.OAUTH_CLIENT_ID, AppConstants.OAUTH_CLIENT_SECRET));

		//fetching  complete URI where app is running
		ip = InetAddress.getLocalHost();

		//preparing URL for rest request
		String accessTokenUrl = AppConstants.HTTP + ip.getHostAddress() + ":" + request.getLocalPort()
				+ request.getContextPath() + AppConstants.ACCESS_TOKEN_URL;

		// setting headers
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> req = new HttpEntity<>(headers);

		//passing prepared URL to URI
		URI uri = new URI(accessTokenUrl);

		response = restTemplate.exchange(uri, HttpMethod.POST, req, TokenResponse.class);

		return response.getBody();
	}

}