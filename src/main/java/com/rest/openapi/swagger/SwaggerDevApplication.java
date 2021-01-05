package com.rest.openapi.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SwaggerDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerDevApplication.class, args);
	}

}
