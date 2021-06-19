package com.ejs.algaworksCurso.core.jackson;

import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.mixin.RestauranteMixin;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class RestauranteMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public RestauranteMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
	}
}
