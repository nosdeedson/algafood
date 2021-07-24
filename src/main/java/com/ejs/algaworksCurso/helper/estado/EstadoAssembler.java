package com.ejs.algaworksCurso.helper.estado;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.in.estado.EstadoIn;
import com.ejs.algaworksCurso.domain.model.Estado;

@Component
public class EstadoAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public Estado estadoInToEstado(EstadoIn estadoIn) {
		return this.mapper.map(estadoIn, Estado.class);
	}
	
	public void estadoInToEstado(Estado estado, EstadoIn estadoIn) {
		this.mapper.map(estadoIn, estado);
	}

}
