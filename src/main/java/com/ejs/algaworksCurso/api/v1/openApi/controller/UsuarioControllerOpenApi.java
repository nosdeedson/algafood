package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.senha.SenhaAtualizarIn;
import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioAtualizarIn;
import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioIn;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.api.v1.openApi.model.UsuariosModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Usuário")
public interface UsuarioControllerOpenApi {

	ResponseEntity<UsuarioOut> atualizar(Long usuarioId, UsuarioAtualizarIn usuarioIn);

	@ApiOperation(value = "Alualiza senha")
	void atualizarSenha(SenhaAtualizarIn senhaIn, Long usuarioId);

	ResponseEntity<UsuarioOut> buscar(Long usuarioId);

	@ApiOperation(value = "Lista os usuários", response = UsuariosModelOpenApi.class)
	ResponseEntity<CollectionModel<UsuarioOut>> listar();
	
	@ApiOperation(value = "Lista os usuários que não estão vinculados ao grupo", response = UsuarioControllerOpenApi.class)
	ResponseEntity<CollectionModel<UsuarioOut>> listarUsuariosNãoVinculadosAoGrupo(
			@ApiParam(value = "O id do grupo sem vínculo com o usuário")Long grupoId);

	void remover(Long usuarioId);

	ResponseEntity<UsuarioOut> salvar(UsuarioIn usuarioIn);

}