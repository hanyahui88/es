package com.alvin.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan
@EnableWebMvc
public class EsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsApplication.class, args);
	}
}
