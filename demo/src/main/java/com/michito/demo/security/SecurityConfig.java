package com.michito.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration//decir que esta clase se van a crear beans
@EnableWebSecurity// todo lo relacionado con seguridad en esta clase
public class SecurityConfig {
    // filtros de seguridad como proxys o middlewares
    @Bean 
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.
            csrf(AbstractHttpConfigurer::disable)//backend y forntend en un mismo nodo
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/h2/**").permitAll()
                .requestMatchers("/Clientes/**").authenticated()
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
