package com.ejs.algaworksCurso.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.model.out.permissao.PermissaoOut;
import com.ejs.algaworksCurso.api.openApi.controller.PermissaoControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.PermissaoService;

@RestController
@RequestMapping("permissoes")
public class PermissaoController implements PermissaoControllerOpenApi {
	
	@Autowired
	PermissaoService permissaoService;

	@Override
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public CollectionModel<PermissaoOut> listar() {
		CollectionModel<PermissaoOut> permissoes = this.permissaoService.listar();
		return permissoes;
	}

}
