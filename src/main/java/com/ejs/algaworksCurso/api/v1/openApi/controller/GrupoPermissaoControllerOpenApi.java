package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.api.v1.openApi.model.PermissoesModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Grupo Permissão")
public interface GrupoPermissaoControllerOpenApi {

	@ApiOperation(value = "Associa grupo a permissão")
	ResponseEntity<Void> associar(Long grupoId, Long permissaoId);

	@ApiOperation(value = "Desassocia grupo de permissão")
	ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);

	@ApiOperation(value = "Lista as permissões por grupo", response = PermissoesModelOpenApi.class)
	ResponseEntity<CollectionModel<PermissaoOut>> listar(Long grupoId);
	
	@ApiOperation(value = "Lista as permissões não vinculadas a um grupo", response = PermissoesModelOpenApi.class)
	ResponseEntity<CollectionModel<PermissaoOut>> listarPermissoesNaoVinculadaGrupo(
			@ApiParam(value = "Através do id do grupo devolve as permissões não vinculadas ao grupo") Long grupoId);

}