package com.michito.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // En esta clase se van a crear beans
@EnableWebSecurity // en esta clase se configura todo lo relacionado con seguridad
public class SecurityConfig {

    @Autowired 
    JwtAuthEntryPoint jwtAuthEntryPoint;
    //FILTER - PROXY - MIDDLEWARE -> CONTROLLER -> SERVICE -> REPOSITORY
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
            csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(requests -> requests

                //ESte si hay que dejarlo para todos 
                .requestMatchers("/Clientes/all**").permitAll()
                .requestMatchers("/h2/**").permitAll()
                // Todos - clientes -- si hay rol de clientes cambiar a has any authority cliente, admin y veterinario 
                
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/Tratamientos/mascota/**").hasAnyAuthority("ADMIN", "VETERINARIO","CLIENTE") 
                .requestMatchers("/Tratamientos/Info/**").hasAnyAuthority("ADMIN", "VETERINARIO","CLIENTE")
                .requestMatchers("/Clientes/por-mascota/**").hasAnyAuthority("ADMIN", "VETERINARIO","CLIENTE")

                // Veterinarios Y admin

                .requestMatchers("/Veterinarios/detalles**").hasAnyAuthority("ADMIN", "VETERINARIO")        
                .requestMatchers("/login**").hasAnyAuthority("ADMIN", "VETERINARIO")
                .requestMatchers("/Clientes**").hasAnyAuthority("ADMIN", "VETERINARIO")
                .requestMatchers("/Medicamentos**").hasAnyAuthority("ADMIN", "VETERINARIO")
                .requestMatchers("/Tratamientos**").hasAnyAuthority("ADMIN", "VETERINARIO")

                
                // Solo Admin 
                .requestMatchers("/Veterinarios/**").hasAuthority("ADMIN")
                .requestMatchers("/kpis/**").hasAuthority("ADMIN")

                .anyRequest().permitAll()
            )
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint));

            http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }

}   
           
