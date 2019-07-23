package com.sis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SisActivaApplication {

	public static void main(String[] args) {
		System.setProperty("server.port","7324");
		SpringApplication.run(SisActivaApplication.class, args);
	}
}
