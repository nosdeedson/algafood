package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.exceptionHandler.Problem;
import com.ejs.algaworksCurso.api.v1.model.in.cidade.CidadeIn;
import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags  = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation(value = "Atualiza cidade")
	ResponseEntity<CidadeOut> atualizar(Long id, CidadeIn cidadeIn);

	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	@ApiOperation("Busca uma cidade por ID")
	ResponseEntity<CidadeOut> buscar(@ApiParam(value = "ID de uma cidade")Long id);

	CollectionModel<CidadeOut> listar();

	void remover(@ApiParam(value = "ID de uma cidade") // anotation for documentation
	Long id);

	ResponseEntity<CidadeOut> salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
	CidadeIn cidadeIn);

}