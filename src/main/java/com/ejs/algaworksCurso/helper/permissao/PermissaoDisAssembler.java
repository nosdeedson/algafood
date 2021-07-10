package com.ejs.algaworksCurso.helper.permissao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.domain.model.Permissao;

@Component
public class PermissaoDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public PermissaoOut permissaoToPermissaoOUt(Permissao permissao) {
		return this.mapper.map(permissao, PermissaoOut.class);
	}
}
