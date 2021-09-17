package com.ejs.algaworksCurso.domain.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.ejs.algaworksCurso.api.v1.controller.RestauranteUsuarioController;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.exception.RestauranteNaoEncontradoException;
import com.ejs.algaworksCurso.domain.exception.UsuarioNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.ejs.algaworksCurso.domain.model.Usuario;
import com.ejs.algaworksCurso.helper.usuario.UsuarioDisAssembler;

@Service
public class RestauranteUsuarioService {

	@Autowired
	private UsuarioService usuarioService;
		
	@Autowired
	private UsuarioDisAssembler usuarioDisAssembler;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Transactional
	public void associarResponsavel(Long restauranteId, Long usuarioId) {
		try {
			Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
			Usuario usuario = this.usuarioService.buscarOuFalhar(usuarioId);
			restaurante.getResponsaveis().add(usuario);
		} catch (RestauranteNaoEncontradoException | UsuarioNaoEncontradoException e) {
			String entity = "";
			Long id = 0L;
			if( e instanceof RestauranteNaoEncontradoException) {
				entity = "Restaurante";
				id = restauranteId;
			}else {
				entity = "Usuario";
				id = usuarioId;
			}
			throw new NegocioException(String.format("%s de código %d não existe.", entity, id));
		}
	}
	
	@Transactional
	public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
		try {
			Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
			Usuario usuario = this.usuarioService.buscarOuFalhar(usuarioId);
			restaurante.getResponsaveis().remove(usuario);
		} catch (RestauranteNaoEncontradoException | UsuarioNaoEncontradoException e) {
			String entity = "";
			Long id = 0L;
			if( e instanceof RestauranteNaoEncontradoException) {
				entity = "Restaurante";
				id = restauranteId;
			}else {
				entity = "Usuario";
				id = usuarioId;
			}
			throw new NegocioException(String.format("%s de código %d não existe.", entity, id));
		}
	}
	
	
	public CollectionModel<UsuarioOut> listarResponsaveis(Long restauranteId) {
		try {
			Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
			
			CollectionModel<UsuarioOut> usuariosOut = usuarioDisAssembler.toCollectionModel(restaurante.getResponsaveis());
			usuariosOut.removeLinks().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioController.class)
					.listarResponsaveis(restauranteId)).withRel("responsaveis"));
			usuariosOut.getContent().stream().forEach(usuario -> {				
				usuario.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioController.class)
						.desassociarResponsavel(restauranteId, usuario.getId())).withRel("desassociar"));
				
				usuario.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioController.class)
						.associarResponsavel(restauranteId, null)).withRel("associar"));
			});
			usuariosOut.getContent().stream().forEach(usuario -> {			
			});
			return usuariosOut;
		} catch (RestauranteNaoEncontradoException | UsuarioNaoEncontradoException e) {
			throw new NegocioException(String.format("Restaurante de código %d não existe.", restauranteId));
		}
	}
}
