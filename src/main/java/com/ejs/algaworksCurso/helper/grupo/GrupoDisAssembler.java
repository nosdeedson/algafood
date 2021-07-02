package com.ejs.algaworksCurso.helper.grupo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.out.group.GrupoOut;
import com.ejs.algaworksCurso.domain.model.Grupo;

@Component
public class GrupoDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public GrupoOut grupoToGrupoOut(Grupo grupo) {
		return this.mapper.map(grupo, GrupoOut.class);
	}
}
