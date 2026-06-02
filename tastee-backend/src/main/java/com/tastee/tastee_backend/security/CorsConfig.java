package com.tastee.tastee_backend.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// Dev CORS config to allow requests from Angular dev server.
@Configuration
public class CorsConfig {

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    
    // NOTE: In production, you should specify the allowed origins instead of allowing all
    config.addAllowedOrigin("http://localhost:8100");  // your Angular dev server
    config.addAllowedMethod("*");   // GET, POST, PUT, DELETE, etc.
    config.addAllowedHeader("*");   // Authorization, Content-Type, etc.
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config); // apply to all routes
    return source;
  }
}
