package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.cozinha.CozinhaIn;
import com.ejs.algaworksCurso.api.v1.model.out.cozinha.CozinhaOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	ResponseEntity<CozinhaOut> atualizar(CozinhaIn cozinhaIn, Long cozinhaId);

	ResponseEntity<CozinhaOut> buscar(Long id);

	@ApiOperation(value = "Busca a primeira cozinha no Banco de dados")
	ResponseEntity<CozinhaOut> buscarPrimeira();

	PagedModel<CozinhaOut> listar(Pageable pageable);

	void remover(Long cozinhaId);

	ResponseEntity<CozinhaOut> salvar(CozinhaIn cozinhaIn);

}