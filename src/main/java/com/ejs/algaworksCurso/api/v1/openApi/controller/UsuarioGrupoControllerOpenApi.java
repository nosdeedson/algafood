package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;


public interface UsuarioGrupoControllerOpenApi {

	ResponseEntity<Void> associarGrupo(Long usuarioId, Long grupoId);

	ResponseEntity<Void> desassociarGrupo(Long usuarioId, Long grupoId);

	ResponseEntity<CollectionModel<GrupoOut>> listar(Long usuarioId);

}