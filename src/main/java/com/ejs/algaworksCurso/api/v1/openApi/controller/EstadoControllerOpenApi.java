package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.estado.EstadoIn;
import com.ejs.algaworksCurso.api.v1.model.out.estado.EstadoOut;
@Tag(name = "Estados")
@SecurityRequirement(name = "bearer-token")
public interface EstadoControllerOpenApi {

	ResponseEntity<EstadoOut> atualizar(Long id, EstadoIn estadoIn);

	ResponseEntity<EstadoOut> buscar(Long id);

	CollectionModel<EstadoOut> listar();

	void remover(Long id);

	ResponseEntity<EstadoOut> salvar(EstadoIn estadoIn);

}