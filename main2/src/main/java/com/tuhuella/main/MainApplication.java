package com.tuhuella.main;

import com.tuhuella.main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
@Autowired
private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}


}
