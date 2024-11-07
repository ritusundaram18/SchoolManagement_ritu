package com.demo.SBDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbDemoApplication.class, args);
	}

	

	//create Docket object by select all apis from base package 
	//and build it for getting Docket object
	@Bean
	public Docket schoolApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.demo.SBDemo")).build();
	}

}
