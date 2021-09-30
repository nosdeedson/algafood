package com.ejs.algaworksCurso.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.RestauranteUsuarioControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.RestauranteUsuarioService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi {
	
	@Autowired
	private RestauranteUsuarioService restauranteUsuarioService;
	
	@Override
	@PutMapping("{usuarioId}")
	public ResponseEntity<Void> associarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		this.restauranteUsuarioService.associarResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("{usuarioId}")
	public ResponseEntity<Void> desassociarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
		this.restauranteUsuarioService.desassociarResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<UsuarioOut>> listarResponsaveis(@PathVariable Long restauranteId){
		CollectionModel<UsuarioOut> usuarios = this.restauranteUsuarioService.listarResponsaveis(restauranteId);
		usuarios.removeLinks();
		usuarios.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteUsuarioController.class)
				.listarResponsaveis(restauranteId)).withSelfRel());
		return ResponseEntity.ok(usuarios);
	}

}
