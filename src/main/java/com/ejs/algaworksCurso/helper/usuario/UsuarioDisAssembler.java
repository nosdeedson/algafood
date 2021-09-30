package com.ejs.algaworksCurso.helper.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.v1.controller.UsuarioController;
import com.ejs.algaworksCurso.api.v1.controller.UsuarioGrupoController;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.domain.model.Usuario;

@Component
public class UsuarioDisAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioOut> {

	public UsuarioDisAssembler() {
		super(UsuarioController.class, UsuarioOut.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UsuarioOut toModel(Usuario entity) {
		
		UsuarioOut out = createModelWithId(entity.getId(), entity);
		
		mapper.map(entity, out);
		
		out.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class)
				.listar(out.getId())).withRel("grupos"));
		return out;
	}
	
	@Override
	public CollectionModel<UsuarioOut> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
				.add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withRel("Usuários"));
	}
}
