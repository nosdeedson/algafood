package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.hateoas.CollectionModel;

import com.ejs.algaworksCurso.api.model.out.permissao.PermissaoOut;

import io.swagger.annotations.Api;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

	public CollectionModel<PermissaoOut> listar();
}
