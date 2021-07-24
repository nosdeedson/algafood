package com.ejs.algaworksCurso.helper.cidade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.in.cidade.CidadeIn;
import com.ejs.algaworksCurso.domain.model.Cidade;
import com.ejs.algaworksCurso.domain.model.Estado;

@Component
public class CidadeAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public Cidade cidadeInToCidade( CidadeIn cidadeIn) {
		return mapper.map(cidadeIn, Cidade.class);
	}

	public void cidadeIntoCidade(Cidade cidade, CidadeIn cidadeIn) {
		cidade.setEstado(new Estado());
		this.mapper.map(cidadeIn, cidade);
	}
}
