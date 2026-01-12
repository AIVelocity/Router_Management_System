package com.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RouterManagementSystemRbacApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouterManagementSystemRbacApplication.class, args);
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		    System.out.println(encoder.encode("subadmin123"));
	}

}
