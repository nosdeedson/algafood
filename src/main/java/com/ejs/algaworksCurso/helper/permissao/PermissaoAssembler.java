package com.ejs.algaworksCurso.helper.permissao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.in.permissao.PermissaoIn;
import com.ejs.algaworksCurso.domain.model.Permissao;

@Component
public class PermissaoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public Permissao permissaoInToPermissao(PermissaoIn permissaoIn) {
		return this.mapper.map(permissaoIn, Permissao.class);
	}
	
	public void permissaoInToPermissao(PermissaoIn permissaoIn, Permissao permissao) {
		this.mapper.map(permissaoIn, permissao);
	}
}
