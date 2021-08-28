package com.ejs.algaworksCurso.api.openApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.out.group.GrupoOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Usuário Grupos")
public interface UsuarioGrupoControllerOpenApi {

	@ApiOperation(value = "Associa usuário a grupo")
	void associarGrupo(Long usuarioId, Long grupoId);

	@ApiOperation(value = "Desassocia usuário a grupo")
	void desassociarGrupo(Long usuarioId, Long grupoId);

	ResponseEntity<List<GrupoOut>> listar(Long usuarioId);

}