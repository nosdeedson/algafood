package com.ejs.algaworksCurso.api.openApi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.ejs.algaworksCurso.api.model.in.produto.FotoProdutoIn;
import com.ejs.algaworksCurso.api.model.out.fotoProduto.FotoProdutoOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Foto Produto")
public interface RestauranteFotoProdutoControllerOpenApi {

	@ApiOperation(value = "Atualiza foto")
	ResponseEntity<FotoProdutoOut> atualizarFoto(
			@ApiParam(value = "Id restaurante", required = true)Long restauranteId, 
			
			@ApiParam(value = "Id produto", required = true) Long produtoId,
			FotoProdutoIn fotoProdutoIn,
			
			@ApiParam(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)", required = true)
			MultipartFile arquivo)
			throws IOException;

	@ApiOperation(value = "Busca os dados da foto de um produto")
	ResponseEntity<FotoProdutoOut> buscarDadosFoto(Long restauranteId, Long produtoId);

	@ApiOperation(value = "Busca foto")
	ResponseEntity<?> buscarFoto(Long restauranteId, Long produtoId, String acceptType)
			throws HttpMediaTypeNotAcceptableException;

	@ApiOperation(value = "Deleta foto de produto")
	void deletarFotoProduto(Long restauranteId, Long produtoId);

}