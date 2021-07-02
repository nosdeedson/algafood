package com.ejs.algaworksCurso.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper createModelMapper() {
		var mapper = new ModelMapper();
		
		return mapper;
	}
}
