package com.flaviosantos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.flaviosantos.services.EmailService;
import com.flaviosantos.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
