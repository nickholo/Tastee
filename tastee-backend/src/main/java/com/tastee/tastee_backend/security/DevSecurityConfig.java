package com.tastee.tastee_backend.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tastee.tastee_backend.beans.Users;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

@Configuration
@Profile("dev")  // only loads when dev profile is active
public class DevSecurityConfig {
    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain devFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // allow everything
            )
            .addFilterBefore(devAuthenticationFilter(), SecurityContextHolderFilter.class)
            .build();
    }
    
    @Bean
    public OncePerRequestFilter devAuthenticationFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                    FilterChain filterChain) throws ServletException, IOException {
                // Create a dummy user for development
                Users dummyUser = new Users();
                dummyUser.setId(Long.valueOf(999));
                dummyUser.setUsername("dev-user");
                
                // Set the dummy user as authenticated in the security context
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                UsernamePasswordAuthenticationToken auth = 
                    new UsernamePasswordAuthenticationToken(dummyUser, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
                
                filterChain.doFilter(request, response);
            }
        };
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}