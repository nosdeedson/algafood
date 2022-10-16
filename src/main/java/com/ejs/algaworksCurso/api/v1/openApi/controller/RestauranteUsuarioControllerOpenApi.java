package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;

public interface RestauranteUsuarioControllerOpenApi {

	ResponseEntity<Void> associarResponsavel(Long restauranteId, Long usuarioId);

	ResponseEntity<Void> desassociarResponsavel(Long restauranteId, Long usuarioId);

	ResponseEntity<CollectionModel<UsuarioOut>> listarResponsaveis(Long restauranteId);

}