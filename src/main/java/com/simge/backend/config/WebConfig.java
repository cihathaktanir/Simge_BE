package com.simge.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for all endpoints in your application
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Replace with your frontend's URL (Angular app)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow common HTTP methods
                .allowedHeaders("*") // Allow any headers
                .allowCredentials(true); // Allow sending cookies (optional, only if needed)
    }
}
