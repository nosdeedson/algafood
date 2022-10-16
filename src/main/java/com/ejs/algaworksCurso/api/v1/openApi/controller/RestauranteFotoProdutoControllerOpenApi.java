package com.ejs.algaworksCurso.api.v1.openApi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.ejs.algaworksCurso.api.v1.model.out.fotoProduto.FotoProdutoOut;

public interface RestauranteFotoProdutoControllerOpenApi {

	ResponseEntity<FotoProdutoOut> atualizarFoto(Long restauranteId,  Long produtoId, MultipartFile arquivo)
			throws IOException;

	ResponseEntity<FotoProdutoOut> buscarDadosFoto(Long restauranteId, Long produtoId);

	ResponseEntity<?> buscarFoto(Long restauranteId, Long produtoId, String acceptType)
			throws HttpMediaTypeNotAcceptableException;

	void deletarFotoProduto(Long restauranteId, Long produtoId);

}