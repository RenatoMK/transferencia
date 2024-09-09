package com.desafioitau.api.transferencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@EntityScan("com.desafioitau.api.transferencia.entity")
public class ApiTransferenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTransferenciaApplication.class, args);
	}
	
}
