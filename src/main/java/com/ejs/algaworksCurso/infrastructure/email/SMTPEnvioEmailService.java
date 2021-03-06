package com.ejs.algaworksCurso.infrastructure.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.domain.services.EnvioEmailService;

import freemarker.template.Configuration;

@Service
public class SMTPEnvioEmailService implements EnvioEmailService{
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private Configuration freeMarkerconfig;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String html = this.processarHtml(mensagem, freeMarkerconfig);
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setText(html, true);
			helper.setSubject(mensagem.getAssunto());
			emailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar o email", e);
		}
		
	}

}
