package com.ejs.algaworksCurso.helper.v2;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v2.controller.CidadeControllerV2;
import com.ejs.algaworksCurso.api.v2.model.out.cidade.CidadeOutV2;
import com.ejs.algaworksCurso.domain.model.Cidade;

@Component
public class CidadeDisAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeOutV2> {

	@Autowired
	private ModelMapper mapper;
	
	public CidadeDisAssemblerV2() {
		super(CidadeControllerV2.class, CidadeOutV2.class);
	}
	
	@Override
	public CidadeOutV2 toModel(Cidade entity) {
		CidadeOutV2 out = createModelWithId(entity.getId(), entity);
		
		mapper.map(entity, out);
		
		out.add(WebMvcLinkBuilder.linkTo(CidadeControllerV2.class)
				.withRel("cidades"));
			
		return out;
	}
	

		
}
