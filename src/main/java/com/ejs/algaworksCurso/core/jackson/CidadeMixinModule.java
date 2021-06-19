package com.ejs.algaworksCurso.core.jackson;

import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.mixin.CidadeMixin;
import com.ejs.algaworksCurso.domain.model.Cidade;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class CidadeMixinModule extends SimpleModule{
	
	private static final long serialVersionUID = 1L;

	public CidadeMixinModule() {
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	}
}
