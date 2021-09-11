package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.in.senha.SenhaAtualizarIn;
import com.ejs.algaworksCurso.api.model.in.usuario.UsuarioAtualizarIn;
import com.ejs.algaworksCurso.api.model.in.usuario.UsuarioIn;
import com.ejs.algaworksCurso.api.model.out.usuario.UsuarioOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Usuário")
public interface UsuarioControllerOpenApi {

	ResponseEntity<UsuarioOut> atualizar(Long usuarioId, UsuarioAtualizarIn usuarioIn);

	@ApiOperation(value = "Alualiza senha")
	void atualizarSenha(SenhaAtualizarIn senhaIn, Long usuarioId);

	ResponseEntity<UsuarioOut> buscar(Long usuarioId);

	ResponseEntity<CollectionModel<UsuarioOut>> listar();

	void remover(Long usuarioId);

	ResponseEntity<UsuarioOut> salvar(UsuarioIn usuarioIn);

}