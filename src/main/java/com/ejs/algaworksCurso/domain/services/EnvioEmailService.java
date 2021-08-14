package com.ejs.algaworksCurso.domain.services;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.ejs.algaworksCurso.infrastructure.email.EmailException;
import com.ejs.algaworksCurso.infrastructure.email.Mensagem;

import freemarker.template.Configuration;
import freemarker.template.Template;

public interface EnvioEmailService {
	
	
	void enviar( Mensagem mensagem);
	
	default String processarHtml(Mensagem mensagem, Configuration freeMarker) {
		
		try {
			Template template = freeMarker.getTemplate(mensagem.getCorpo());
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException("Não foi possível gerar o templatE do email");
		}
	}
}