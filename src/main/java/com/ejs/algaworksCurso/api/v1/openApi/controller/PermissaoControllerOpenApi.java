package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.permissao.PermissaoIn;
import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.api.v1.openApi.model.PermissoesModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

	@ApiOperation(value = "Atualiza permissão")
	public ResponseEntity<PermissaoOut> atualizar(Long permissaoId, PermissaoIn in);
		
	@ApiOperation(value = "Lista as permissões", response = PermissoesModelOpenApi.class)
	public CollectionModel<PermissaoOut> listar();
	
	public void remover(@ApiParam(value = "Id da permissão") Long permissaoId);
	
	public ResponseEntity<PermissaoOut> salvar(PermissaoIn in);
}
