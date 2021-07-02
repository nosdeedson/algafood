package com.ejs.algaworksCurso.helper.restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.in.restaurante.RestauranteIn;
import com.ejs.algaworksCurso.domain.model.Restaurante;

@Component
public class RestauranteAssembler {
	
	@Autowired
	private  ModelMapper modelMapper;
	
	public  Restaurante restauranteInToRestaurante( RestauranteIn in) {
		return modelMapper.map(in, Restaurante.class);
	}
	
	public  void restauranteInToRestaurante(RestauranteIn restauranteIn, Restaurante restaurante) {
		modelMapper.map(restauranteIn, restaurante);
	}

}
