package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.exceptionHandler.Problem;
import com.ejs.algaworksCurso.api.model.in.cidade.CidadeIn;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags  = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation(value = "Atualiza cidade")
	ResponseEntity<?> atualizar(Long id, CidadeIn cidadeIn);

	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	@ApiOperation("Busca uma cidade por ID")
	ResponseEntity<?> buscar(@ApiParam(value = "ID de uma cidade", example = "1")
	Long id);

	ResponseEntity<?> listar();

	ResponseEntity<?> remover(@ApiParam(value = "ID de uma cidade", example = "1") // anotation for documentation
	Long id);

	ResponseEntity<?> salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
	CidadeIn cidadeIn);

}