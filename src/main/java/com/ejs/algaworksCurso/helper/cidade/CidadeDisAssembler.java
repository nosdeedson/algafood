package com.ejs.algaworksCurso.helper.cidade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.out.cidade.CidadeOut;
import com.ejs.algaworksCurso.domain.model.Cidade;

@Component
public class CidadeDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public CidadeOut cidadeToCidadeOut(Cidade cidade) {
		return this.mapper.map(cidade, CidadeOut.class);
	}
}
