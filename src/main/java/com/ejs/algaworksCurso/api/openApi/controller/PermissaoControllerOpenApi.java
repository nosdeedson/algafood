package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.hateoas.CollectionModel;

import com.ejs.algaworksCurso.api.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.api.openApi.model.PermissoesModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

	@ApiOperation(value = "Lista as permissões", response = PermissoesModelOpenApi.class)
	public CollectionModel<PermissaoOut> listar();
}
