package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.produto.ProdutoIn;
import com.ejs.algaworksCurso.api.v1.model.in.produto.ProdutoOut;
@Tag(name = "Restaurante/Produto")
@SecurityRequirement(name = "bearer-token")
public interface RestauranteProdutoControllerOpenApi {

	ResponseEntity<ProdutoOut> atualizarProduto(Long restauranteId, Long produtoId, ProdutoIn produtoIn);

	ResponseEntity<ProdutoOut> buscarProduto(Long restauranteId, Long produtoId);

	ResponseEntity<CollectionModel<ProdutoOut>> listarProduto(Long restauranteId, Boolean incluirInativos);

	void remover(Long restauranteId, Long produtoId);

	ResponseEntity<ProdutoOut> salvar(Long restauranteId, ProdutoIn produtoIn);

}