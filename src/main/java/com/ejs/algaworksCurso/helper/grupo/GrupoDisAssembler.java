package com.ejs.algaworksCurso.helper.grupo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.controller.GrupoController;
import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;
import com.ejs.algaworksCurso.domain.model.Grupo;

@Component
public class GrupoDisAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoOut> {

	public GrupoDisAssembler() {
		super(GrupoController.class, GrupoOut.class);
	}

	@Autowired
	private ModelMapper mapper;
	@Override
	public GrupoOut toModel(Grupo entity) {
		GrupoOut out = createModelWithId(entity.getId(), entity);
		mapper.map(entity, out);
		out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GrupoController.class)
				.listar()).withRel("grupos"));
		return out;
	}
	
	@Override
	public CollectionModel<GrupoOut> toCollectionModel(Iterable<? extends Grupo> entities) {
		return super.toCollectionModel(entities);
	}
}
