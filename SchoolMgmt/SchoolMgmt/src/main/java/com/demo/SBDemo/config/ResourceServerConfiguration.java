package com.demo.SBDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.demo.SBDemo.utils.AppConstants;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(AppConstants.SERVER_RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers(AppConstants.APP_ROOT_API+AppConstants.STUDENT_ROOT_API).permitAll()       //=>  /api/v1/students
		    .antMatchers(AppConstants.APP_ROOT_API+AppConstants.LOGIN_ROOT_API).permitAll()         //=>  /api/v1/login
		    .antMatchers(AppConstants.APP_ROOT_API+AppConstants.STUDENT_ROOT_API+"/*").permitAll()  //=>  /api/v1/students/1
		    .antMatchers(AppConstants.APP_ROOT_API+AppConstants.STUDENT_ROOT_API+"/*/*").permitAll()  //=>  /api/v1/students/1/username
		    .antMatchers(AppConstants.APP_ROOT_API+AppConstants.STUDENT_ROOT_API+
		    			 AppConstants.STUDENT_ROOT_API_NAME+"/*").permitAll() // => /api/v1/students/byName/ram
		    .antMatchers(AppConstants.APP_ROOT_API+AppConstants.TEACHER_ROOT_API).permitAll()       //=>  /api/v1/teacher
		    .antMatchers(AppConstants.APP_ROOT_API+AppConstants.TEACHER_ROOT_API+"/*").permitAll()       //=>  /api/v1/teacher
		    .regexMatchers("\\/api\\/v1\\/locale\\?lang=hn(&.*|$)").permitAll()
		    .regexMatchers("\\/api\\/v1\\/locale\\?lang=en(&.*|$)").permitAll()
		    .regexMatchers("\\/api\\/v1\\/locale\\?lang=cn(&.*|$)").permitAll()
		    .antMatchers("/api/v1/**")
		    .authenticated();
	}

}
