package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.configs.CacheConfiguration;

@SpringBootApplication
@Import(CacheConfiguration.class)
public class CoherenceSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoherenceSpringBootApplication.class, args);
	}

}
