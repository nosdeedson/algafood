package com.ejs.algaworksCurso.helper.cozinha;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.out.CozinhaOut;
import com.ejs.algaworksCurso.domain.model.Cozinha;

@Component
public class CozinhaDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public CozinhaOut cozinhaToCozinhaOut(Cozinha cozinha) {
		return mapper.map(cozinha, CozinhaOut.class);
	}
}
