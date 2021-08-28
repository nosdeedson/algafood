package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.in.grupo.GrupoIn;

import io.swagger.annotations.Api;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	ResponseEntity<?> atualizar(GrupoIn grupoIn, Long grupoId);

	ResponseEntity<?> buscar(Long grupoId);

	ResponseEntity<?> listar();

	ResponseEntity<?> remover(Long grupoId);

	ResponseEntity<?> salvar(GrupoIn grupoIn);

}