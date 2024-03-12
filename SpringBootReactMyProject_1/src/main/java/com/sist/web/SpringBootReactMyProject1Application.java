package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sist.web.controller", "com.sist.web.entity", "com.sist.web.dao"})
public class SpringBootReactMyProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactMyProject1Application.class, args);
	}

}
