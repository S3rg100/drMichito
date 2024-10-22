package com.michito.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public JavaMailSender mailSender() {
        return mock(JavaMailSender.class); // Simula el bean de JavaMailSender
    }
}
