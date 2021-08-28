package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.StringUriResposta;
import com.ejs.algaworksCurso.api.model.in.cozinha.CozinhaIn;
import com.ejs.algaworksCurso.api.model.out.cozinha.CozinhaOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	ResponseEntity<StringUriResposta> atualizar(CozinhaIn cozinhaIn, Long cozinhaId);

	ResponseEntity<CozinhaOut> buscar(Long id);

	@ApiOperation(value = "Busca a primeira cozinha no Banco de dados")
	ResponseEntity<CozinhaOut> buscarPrimeira();

	Page<CozinhaOut> listar(Pageable pageable);

	void remover(Long cozinhaId);

	ResponseEntity<StringUriResposta> salvar(CozinhaIn cozinhaIn);

}