package com.ejs.algaworksCurso.helper.estado;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.controller.EstadoController;
import com.ejs.algaworksCurso.api.v1.model.out.estado.EstadoOut;
import com.ejs.algaworksCurso.domain.model.Estado;

@Component
public class EstadoDisAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoOut> {

	public EstadoDisAssembler() {
		super(EstadoController.class, EstadoOut.class);
	}

	@Autowired
	private ModelMapper mapper;
	

	@Override
	public EstadoOut toModel(Estado entity) {
		EstadoOut out = createModelWithId(entity.getId(), entity);
		mapper.map(entity, out);
		out.add(WebMvcLinkBuilder.linkTo(EstadoController.class).withRel("estados"));
		return out;
	}
	
	@Override
	public CollectionModel<EstadoOut> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
