package com.speedlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
@ComponentScan(basePackages = "com.speedlog")
@EnableMongoRepositories
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SpeedLog API", version = "2.0", description = "SpeedLog Info"))
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
