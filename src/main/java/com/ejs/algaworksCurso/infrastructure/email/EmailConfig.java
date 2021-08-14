package com.ejs.algaworksCurso.infrastructure.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ejs.algaworksCurso.domain.services.EnvioEmailService;

@Configuration
public class EmailConfig {
	
//	@Value("${algafood.email.smtp}")
//	private String smtp;
	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	@Primary
	public EnvioEmailService envioEmailService() {
		switch (emailProperties.getTipoEnvio()) {
		case "fake":
			return new FakeEnvioEmailService();
		case "smtp":
			return new SMTPEnvioEmailService();
		case "sandbox":
			return new SandBoxEnvioEmailService();
		default:
			return null;
		}
	}

}
