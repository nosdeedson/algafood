package com.ejs.algaworksCurso.helper.cozinha;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.model.in.cozinha.CozinhaIn;
import com.ejs.algaworksCurso.domain.model.Cozinha;

@Component
public class CozinhaAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public Cozinha cozinhaInToCozinha(CozinhaIn cozinhaIn) {
		return this.mapper.map(cozinhaIn, Cozinha.class);
	}
	
	public void cozinhaInToCozinha( Cozinha cozinha, CozinhaIn cozinhaIn) {
		this.mapper.map(cozinhaIn, cozinha);
	}
}
