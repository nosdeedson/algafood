package com.ejs.algaworksCurso.helper.estado;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.out.estado.EstadoOut;
import com.ejs.algaworksCurso.domain.model.Estado;

@Component
public class EstadoDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public EstadoOut estadoToEstadoOut(Estado estado) {
		return mapper.map(estado, EstadoOut.class);
	}
}
