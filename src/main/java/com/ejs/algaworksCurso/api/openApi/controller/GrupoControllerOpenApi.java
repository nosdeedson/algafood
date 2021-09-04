package com.ejs.algaworksCurso.api.openApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.in.grupo.GrupoIn;
import com.ejs.algaworksCurso.api.model.out.group.GrupoOut;

import io.swagger.annotations.Api;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	ResponseEntity<GrupoOut> atualizar(GrupoIn grupoIn, Long grupoId);

	ResponseEntity<GrupoOut> buscar(Long grupoId);

	ResponseEntity<List<GrupoOut>> listar();

	void remover(Long grupoId);

	ResponseEntity<GrupoOut> salvar(GrupoIn grupoIn);

}