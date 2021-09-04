package com.ejs.algaworksCurso.api.openApi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.model.in.restaurante.RestauranteIn;
import com.ejs.algaworksCurso.api.model.out.restautante.RestauranteOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApin {

	@ApiOperation(value = "Abre o restaurante")
	void abrir(Long restauranteId);

	@ApiOperation(value = "Ativa restaurante")
	void ativar(Long restauranteId);

	@ApiOperation(value = "Ativa vários restaurantes")
	void ativarMultiplos(List<Long> restauranteIds);

	ResponseEntity<RestauranteOut> atualizar(Long restauranteId, RestauranteIn restaurante);

	ResponseEntity<RestauranteOut> buscar(Long restauranteId);

	@ApiOperation(value = "Encontra restaurantes com frete grátis")
	ResponseEntity<List<RestauranteOut>> encontrarComFreteGratis(String nome);

	@ApiOperation(value = "Encontra o primeiro restaurante")
	ResponseEntity<RestauranteOut> encontrarPrimeiro();

	@ApiOperation(value = "Fecha o restaurante")
	void fechar(Long restauranteId);

	ResponseEntity<List<RestauranteOut>> listar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	@ApiOperation(value = "Inativa o restaurante")
	void inativar(Long restauranteId);
 
	@ApiOperation(value = "Inativa vários restaurantes")
	void inativarMultiplos(List<Long> restauranteIds);

	ResponseEntity<List<RestauranteOut>> listar();

	ResponseEntity<RestauranteOut> salvar(RestauranteIn restauranteIn);

}