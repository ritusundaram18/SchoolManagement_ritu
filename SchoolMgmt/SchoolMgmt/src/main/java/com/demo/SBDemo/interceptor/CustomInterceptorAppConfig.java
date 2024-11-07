package com.demo.SBDemo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Component
public class CustomInterceptorAppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private CustomInterceptor customInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(customInterceptor);
	}

}
