package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Spring Security works in high level:
        // execute filter chains
        // 1) All requests should be authenticated
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // 2) If a request is not authenticated, a web page(default is login in page) is shown
        http.httpBasic(withDefaults());
        // 3) CSFR -> POST, PUT
        // http.csrf().disable();  ----> deprecated method, use Lambda expression to solve this issue;
        http.csrf(csrf -> csrf.disable());

        // If we want to change the filter chain of spring security, we need to create configure file by ourselves
        return http.build();
    }
}
