package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.grupo.GrupoIn;
import com.ejs.algaworksCurso.api.v1.model.in.grupo.GrupoPermissoesIn;
import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;

public interface GrupoControllerOpenApi {

	ResponseEntity<GrupoOut> atualizar(GrupoIn grupoIn, Long grupoId);

	ResponseEntity<GrupoOut> buscar(Long grupoId);

	ResponseEntity<CollectionModel<GrupoOut>> listar();
	
	public ResponseEntity<CollectionModel<UsuarioOut>> listarUsuariosPorGrupo(Long grupoId);

	void remover(Long grupoId);

	ResponseEntity<GrupoOut> salvar(GrupoIn grupoIn);
	
	ResponseEntity<GrupoOut> salvarGrupoAssociarPermissoes(GrupoPermissoesIn in);

}