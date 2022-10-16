package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.cozinha.CozinhaIn;
import com.ejs.algaworksCurso.api.v1.model.out.cozinha.CozinhaOut;

public interface CozinhaControllerOpenApi {

	ResponseEntity<CozinhaOut> atualizar(CozinhaIn cozinhaIn, Long cozinhaId);

	ResponseEntity<CozinhaOut> buscar(Long id);

	ResponseEntity<CozinhaOut> buscarPrimeira();

	PagedModel<CozinhaOut> listar(Pageable pageable);

	void remover(Long cozinhaId);

	ResponseEntity<CozinhaOut> salvar(CozinhaIn cozinhaIn);

}