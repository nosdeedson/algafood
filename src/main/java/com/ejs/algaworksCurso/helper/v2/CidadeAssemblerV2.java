package com.ejs.algaworksCurso.helper.v2;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v2.model.in.cidade.CidadeInV2;
import com.ejs.algaworksCurso.domain.model.Cidade;
import com.ejs.algaworksCurso.domain.model.Estado;

@Component
public class CidadeAssemblerV2 {
	
	@Autowired
	private ModelMapper mapper;
	
	public Cidade cidadeInToCidade( CidadeInV2 cidadeIn) {
		return mapper.map(cidadeIn, Cidade.class);
	}

	public void cidadeIntoCidade(Cidade cidade, CidadeInV2 cidadeIn) {
		cidade.setEstado(new Estado());
		this.mapper.map(cidadeIn, cidade);
	}
}
