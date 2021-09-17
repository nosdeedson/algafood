package com.ejs.algaworksCurso.helper.permissao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.controller.PermissaoController;
import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.domain.model.Permissao;

@Component
public class PermissaoDisAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoOut> {

	@Autowired
	private ModelMapper mapper;

	public PermissaoDisAssembler() {
		super(PermissaoController.class, PermissaoOut.class);
	}

	@Override
	public PermissaoOut toModel(Permissao entity) {
		PermissaoOut out = createModelWithId(entity.getId(), entity);
		mapper.map(entity, out);
		out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PermissaoController.class)
				.listar()).withRel("permissoes"));		
		return out;
	}
	
	@Override
	public CollectionModel<PermissaoOut> toCollectionModel(Iterable<? extends Permissao> entities) {
		return super.toCollectionModel(entities);
	}
}
