package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.out.permissao.PermissaoOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Grupo Permissão")
public interface GrupoPermissaoControllerOpenApi {

	@ApiOperation(value = "Associa grupo a permissão")
	ResponseEntity<Void> associar(Long grupoId, Long permissaoId);

	@ApiOperation(value = "Desassocia grupo de permissão")
	ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);

	ResponseEntity<CollectionModel<PermissaoOut>> listar(Long grupoId);

}