package com.ejs.algaworksCurso.infrastructure.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ejs.algaworksCurso.domain.services.EnvioEmailService;

import freemarker.template.Configuration;

public class SandBoxEnvioEmailService implements EnvioEmailService{
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration freeMarker;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String html = this.processarHtml(mensagem, freeMarker);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setSubject(mensagem.getAssunto());
			helper.setFrom(emailProperties.getRemetente());
			helper.setText(html, true);
			helper.setTo(emailProperties.getDestinatario());
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar o email", e);
		}
	}

}
