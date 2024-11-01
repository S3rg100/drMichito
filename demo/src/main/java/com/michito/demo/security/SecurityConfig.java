package com.michito.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // En esta clase se van a crear beans
@EnableWebSecurity // en esta clase se configura todo lo relacionado con seguridad
public class SecurityConfig {
    //FILTER - PROXY - MIDDLEWARE -> CONTROLLER -> SERVICE -> REPOSITORY
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
            csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/h2/**").permitAll()
                .requestMatchers("/Clientes/**").authenticated()
                .anyRequest().permitAll()
            );
        return http.build();
    }
}   
           
