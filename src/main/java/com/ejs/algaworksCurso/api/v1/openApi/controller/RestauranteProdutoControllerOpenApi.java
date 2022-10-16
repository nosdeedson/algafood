package com.ejs.algaworksCurso.api.v1.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.produto.ProdutoIn;
import com.ejs.algaworksCurso.api.v1.model.in.produto.ProdutoOut;

public interface RestauranteProdutoControllerOpenApi {

	ResponseEntity<ProdutoOut> atualizarProduto(Long restauranteId, Long produtoId, ProdutoIn produtoIn);

	ResponseEntity<ProdutoOut> buscarProduto(Long restauranteId, Long produtoId);

	ResponseEntity<CollectionModel<ProdutoOut>> listarProduto(Long restauranteId, Boolean incluirInativos);

	void remover(Long restauranteId, Long produtoId);

	ResponseEntity<ProdutoOut> salvar(Long restauranteId, ProdutoIn produtoIn);

}