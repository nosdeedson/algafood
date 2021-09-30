package com.ejs.algaworksCurso.helper.cidade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.controller.CidadeController;
import com.ejs.algaworksCurso.api.v1.controller.EstadoController;
import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;
import com.ejs.algaworksCurso.domain.model.Cidade;

@Component
public class CidadeDisAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeOut> {

	public CidadeDisAssembler() {
		super(CidadeController.class, CidadeOut.class);
	}
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CidadeOut toModel(Cidade entity) {
		CidadeOut out = createModelWithId(entity.getId(), entity);
		
		mapper.map(entity, out);
		 out.add(WebMvcLinkBuilder.linkTo(
				 WebMvcLinkBuilder.methodOn(CidadeController.class)
				 .listar())
				 .withRel("cidades"));
		 
		 out.getEstado().add(WebMvcLinkBuilder.linkTo(
				 WebMvcLinkBuilder.methodOn(EstadoController.class)
				 .buscar(out.getEstado().getId())).withSelfRel());
		
		return out;
	}
	
	@Override
	public CollectionModel<CidadeOut> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
			.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
	}
	
	
	
}
