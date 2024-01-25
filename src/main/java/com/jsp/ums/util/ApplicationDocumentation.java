package com.jsp.ums.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {


	Contact contact() {
		
		return new Contact().email("jhg@gmail.com")
				.url("xyz.abc.in")
				.email("abc");
				
				
				
	}
	
	
	Info info() {
		
		return new Info()
				.title("User Management System API")
				.version("1.0v")
				.description("User Management system is a RESTFUL API built using" + "Spring Boot and Mysql database")
				.contact(contact());
				
		
		
		
	}
	
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
	
}
