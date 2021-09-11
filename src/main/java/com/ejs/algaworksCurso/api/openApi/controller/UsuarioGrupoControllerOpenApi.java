package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.out.group.GrupoOut;

import io.swagger.annotations.ApiOperation;

@Relation(collectionRelation = "grupos")
public interface UsuarioGrupoControllerOpenApi {

	@ApiOperation(value = "Associa usuário a grupo")
	ResponseEntity<Void> associarGrupo(Long usuarioId, Long grupoId);

	@ApiOperation(value = "Desassocia usuário a grupo")
	ResponseEntity<Void> desassociarGrupo(Long usuarioId, Long grupoId);

	ResponseEntity<CollectionModel<GrupoOut>> listar(Long usuarioId);

}