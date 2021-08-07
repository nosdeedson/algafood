package com.ejs.algaworksCurso.infrastructure.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ejs.algaworksCurso.domain.services.EnvioEmailService;

public class SMTPEnvioEmailService implements EnvioEmailService{
	
	@Autowired
	private JavaMailSender emailSender;
	
	private EmailProperties emailProperties;

	@Override
	public void enviar(Menssagem menssagem) {
		
		try {
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(menssagem.getDestinatarios().toArray(new String[0]));
			helper.setText(menssagem.getCorpo(), true);
			helper.setSubject(menssagem.getAssunto());
			emailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar o email", e);
		}
		
	}

}
