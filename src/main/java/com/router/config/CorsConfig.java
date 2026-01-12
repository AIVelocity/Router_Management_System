package com.router.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
 
import java.util.Arrays;
 
@Configuration
public class CorsConfig {
 
    @Value("${cors.allowedOrigins}")
    private String[] allowedOrigins;
 
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
 
        // Load origins dynamically from application.properties
        config.setAllowedOrigins(Arrays.asList(allowedOrigins));
 
        // Allow all HTTP methods
        config.addAllowedMethod("*");
 
        // Allow all headers
        config.addAllowedHeader("*");
 
        // Allow credentials (for cookies or tokens)
        config.setAllowCredentials(true);
 
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}