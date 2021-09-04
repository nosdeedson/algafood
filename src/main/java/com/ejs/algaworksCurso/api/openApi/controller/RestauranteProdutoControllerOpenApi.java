package com.ejs.algaworksCurso.api.openApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.in.produto.ProdutoIn;
import com.ejs.algaworksCurso.api.model.in.produto.ProdutoOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Restaurantes Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@ApiOperation(value = "Atualiza Produto de restaurante")
	ResponseEntity<ProdutoOut> atualizarProduto(Long restauranteId, Long produtoId, ProdutoIn produtoIn);

	@ApiOperation(value = "Busca produto")
	ResponseEntity<ProdutoOut> buscarProduto(Long restauranteId, Long produtoId);

	@ApiOperation(value = "Lista produto")
	ResponseEntity<List<ProdutoOut>> listarProduto(Long restauranteId, boolean incluirInativos);

	void remover(Long restauranteId, Long produtoId);

	ResponseEntity<ProdutoOut> salvar(Long restauranteId, ProdutoIn produtoIn);

}