package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.permissao.PermissaoIn;
import com.ejs.algaworksCurso.api.v1.model.out.permissao.PermissaoOut;

@Tag(name = "Permissões")
public interface PermissaoControllerOpenApi {

	public ResponseEntity<PermissaoOut> atualizar(Long permissaoId, PermissaoIn in);
		
	public CollectionModel<PermissaoOut> listar();
		
	public ResponseEntity<PermissaoOut> salvar(PermissaoIn in);
}
