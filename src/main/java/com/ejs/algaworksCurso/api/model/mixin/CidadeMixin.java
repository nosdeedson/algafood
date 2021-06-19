package com.ejs.algaworksCurso.api.model.mixin;

import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
public abstract class CidadeMixin {
	
	@JsonIgnoreProperties(allowGetters = true, value = "nome")
	private Estado estado;
}
