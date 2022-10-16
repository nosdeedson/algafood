package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.cidade.CidadeIn;
import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;


public interface CidadeControllerOpenApi {

	ResponseEntity<CidadeOut> atualizar(Long id, CidadeIn cidadeIn);

	ResponseEntity<CidadeOut> buscar(Long id);

	CollectionModel<CidadeOut> listar();

	void remover( Long id);

	ResponseEntity<CidadeOut> salvar(CidadeIn cidadeIn);

}