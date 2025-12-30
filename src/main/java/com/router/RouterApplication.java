package com.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RouterApplication extends SpringBootServletInitializer {

    // For external Tomcat
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RouterApplication.class);
    }

    // For embedded Tomcat
    public static void main(String[] args) {
        SpringApplication.run(RouterApplication.class, args);
    }
}
