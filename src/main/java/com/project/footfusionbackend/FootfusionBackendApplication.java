package com.project.footfusionbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FootfusionBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootfusionBackendApplication.class, args);
	}

}
