package com.ejs.algaworksCurso.infrastructure.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ejs.algaworksCurso.domain.services.EnvioEmailService;

import freemarker.template.Configuration;

public class FakeEnvioEmailService implements EnvioEmailService{
	
	private Logger logger = LoggerFactory.getLogger(SMTPEnvioEmailService.class);

	@Autowired
	private Configuration freeMarkerconfig;
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String html = this.processarHtml(mensagem, freeMarkerconfig);
			logger.info("enviando email fake");
			logger.info(html);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar o email", e);
		}
				
	}

}
