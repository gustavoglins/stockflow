package com.stockflow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply for all routes
                .allowedOrigins("http://localhost:8080") // Allows specific origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allows specific HTTP Methods
                .allowedHeaders("*") // Allows all headers
                .allowCredentials(true); // Allows credentials (cookies, auth...)
    }
}
