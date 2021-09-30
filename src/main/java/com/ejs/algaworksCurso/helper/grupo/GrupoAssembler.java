package com.ejs.algaworksCurso.helper.grupo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.model.in.grupo.GrupoIn;
import com.ejs.algaworksCurso.domain.model.Grupo;

@Component
public class GrupoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public Grupo grupoInToGrupo( GrupoIn grupoIn) {
		return this.mapper.map(grupoIn, Grupo.class);
	}
	
	public void grupoIntoGrupo( GrupoIn grupoIn, Grupo grupo) {
		this.mapper.map(grupoIn, grupo);
	}
}
