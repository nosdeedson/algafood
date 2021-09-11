package com.ejs.algaworksCurso.helper.cozinha;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.controller.CozinhaController;
import com.ejs.algaworksCurso.api.model.out.cozinha.CozinhaOut;
import com.ejs.algaworksCurso.domain.model.Cozinha;

@Component
public class CozinhaDisAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaOut> {

	public CozinhaDisAssembler() {
		super(CozinhaController.class, CozinhaOut.class);
	}

	@Autowired
	private ModelMapper mapper;


	@Override
	public CozinhaOut toModel(Cozinha entity) {
		CozinhaOut out = createModelWithId(entity.getId(), entity);
		mapper.map(entity, out);
		
		out.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel("cozinhas"));
		return out;
	}
}
