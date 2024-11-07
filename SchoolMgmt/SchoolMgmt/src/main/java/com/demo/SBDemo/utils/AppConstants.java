package com.demo.SBDemo.utils;

public class AppConstants {

	// app level api
	public static final String APP_ROOT_API = "/api/v1";

	// student api
	public static final String STUDENT_ROOT_API = "/students";
	public static final String STUDENT_ROOT_API_STU_ID = "/{stuId}";
	public static final String STUDENT_ROOT_API_NAME_AND_STU_ID = "byName/{stuName}";
	public static final String STUDENT_ROOT_API_NAME = "/byName";
	public static final String ROOT_API_LOGGED_USERNAME = "/{loggedInUsername}";

	// teacher API
	public static final String TEACHER_ROOT_API = "/teachers";
	public static final String TEACHER_ROOT_API_TECH_ID = "/{teachId}";

	// login api
	public static final String LOGIN_ROOT_API = "/login";

	// I18N
	public static final String LOCALE_ROOT_API = "/locale";

	public static final String SERVER_RESOURCE_ID = "school-mgmt-backend-api";

	// Oauth config
	public static final String OAUTH_CLIENT_ID = "schoolmgmt";
	public static final String OAUTH_CLIENT_SECRET = "Password@1234";
	public static final String OAUTH_AUTH_TYPE = "password"; // authenticating user by password
	public static final String OAUTH_GRANT_TYPE = "client_credentials"; // allowing user as per there grant type
	public static final String OAUTH_REFRESH_TOKEN = "refresh_token"; // allowing user as per there grant type
	public static final String SCOPE_READ = "read";
	public static final String SCOPE_WRITE = "write";

	public static final String OAUTH_TOKEN_URL = "/oauth/token";

	public static final String ACCESS_TOKEN_URL = "/oauth/token?grant_type=client_credentials";
	public static final String HTTP = "http://";

	//
	public static final String WRONG_USERNAME_PASSWORD = "username or password is wrong";
	public static final String USERNAME_NOT_FOUND = "username not found";
	public static final String ACCESS_TOKEN_MISMATCH = "invalid or access token is missing";

}
