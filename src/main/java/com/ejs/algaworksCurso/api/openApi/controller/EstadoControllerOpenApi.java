package com.ejs.algaworksCurso.api.openApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.in.estado.EstadoIn;
import com.ejs.algaworksCurso.api.model.out.estado.EstadoOut;

import io.swagger.annotations.Api;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	ResponseEntity<EstadoOut> atualizar(Long id, EstadoIn estadoIn);

	ResponseEntity<EstadoOut> buscar(Long id);

	List<EstadoOut> listar();

	void remover(Long id);

	ResponseEntity<EstadoOut> salvar(EstadoIn estadoIn);

}