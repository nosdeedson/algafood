package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.senha.SenhaAtualizarIn;
import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioAtualizarIn;
import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioIn;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;

public interface UsuarioControllerOpenApi {

	ResponseEntity<UsuarioOut> atualizar(Long usuarioId, UsuarioAtualizarIn usuarioIn);

	void atualizarSenha(SenhaAtualizarIn senhaIn, Long usuarioId);

	ResponseEntity<UsuarioOut> buscar(Long usuarioId);

	ResponseEntity<CollectionModel<UsuarioOut>> listar();
	
	ResponseEntity<CollectionModel<UsuarioOut>> listarUsuariosNãoVinculadosAoGrupo(Long grupoId);

	void remover(Long usuarioId);

	ResponseEntity<UsuarioOut> salvar(UsuarioIn usuarioIn);

}