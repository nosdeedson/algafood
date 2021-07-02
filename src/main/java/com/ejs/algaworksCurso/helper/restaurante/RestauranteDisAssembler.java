package com.ejs.algaworksCurso.helper.restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.out.restautante.RestauranteOut;
import com.ejs.algaworksCurso.domain.model.Restaurante;

@Component
public class RestauranteDisAssembler {
	
	@Autowired
	private ModelMapper mapper;

	public RestauranteOut restauranteToRestauranteOut( Restaurante restaurante ) {
		return mapper.map(restaurante, RestauranteOut.class);
	}
	
	
	
}
