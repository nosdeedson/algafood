package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Usuário Grupos")
public interface UsuarioGrupoControllerOpenApi {

	@ApiOperation(value = "Associa usuário a grupo")
	ResponseEntity<Void> associarGrupo(Long usuarioId, Long grupoId);

	@ApiOperation(value = "Desassocia usuário a grupo")
	ResponseEntity<Void> desassociarGrupo(Long usuarioId, Long grupoId);

	ResponseEntity<CollectionModel<GrupoOut>> listar(Long usuarioId);

}