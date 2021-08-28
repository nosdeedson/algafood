package com.ejs.algaworksCurso.api.openApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.out.permissao.PermissaoOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Grupo Permissão", description  = "Gerencia as permissões dos grupos")
public interface GrupoPermissaoControllerOpenApi {

	@ApiOperation(value = "Associa grupo a permissão")
	void associar(Long grupoId, Long permissaoId);

	@ApiOperation(value = "Desassocia grupo de permissão")
	void desassociar(Long grupoId, Long permissaoId);

	ResponseEntity<List<PermissaoOut>> listar(Long grupoId);

}