package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;
@Tag(name = "Usuários/Grupo")
@SecurityRequirement(name = "bearer-token")
public interface UsuarioGrupoControllerOpenApi {

	ResponseEntity<Void> associarGrupo(Long usuarioId, Long grupoId);

	ResponseEntity<Void> desassociarGrupo(Long usuarioId, Long grupoId);

	ResponseEntity<CollectionModel<GrupoOut>> listar(Long usuarioId);

}