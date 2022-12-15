package com.dochero.documentrevisionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DocumentRevisionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentRevisionServiceApplication.class, args);
	}

}
