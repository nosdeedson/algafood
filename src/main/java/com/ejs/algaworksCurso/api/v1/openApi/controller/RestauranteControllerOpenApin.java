package com.ejs.algaworksCurso.api.v1.openApi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.ejs.algaworksCurso.api.v1.model.in.restaurante.RestauranteIn;
import com.ejs.algaworksCurso.api.v1.model.out.restautante.RestauranteOut;
import com.ejs.algaworksCurso.api.v1.openApi.model.RestaurantesModelOpenApi;


public interface RestauranteControllerOpenApin {

	ResponseEntity<Void> abrir(Long restauranteId);

	ResponseEntity<Void> ativar(Long restauranteId);

	void ativarMultiplos(List<Long> restauranteIds);

	ResponseEntity<RestauranteOut> atualizar(Long restauranteId, RestauranteIn restaurante);

	ResponseEntity<RestauranteOut> buscar(Long restauranteId);

	ResponseEntity<List<RestauranteOut>> encontrarComFreteGratis(String nome);

	ResponseEntity<RestauranteOut> encontrarPrimeiro();

	ResponseEntity<Void> fechar(Long restauranteId);

	ResponseEntity<CollectionModel<RestauranteOut>> listar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	ResponseEntity<Void> inativar(Long restauranteId);
 
	void inativarMultiplos(List<Long> restauranteIds);

	ResponseEntity<CollectionModel<RestauranteOut>> listar();

	ResponseEntity<RestauranteOut> salvar(RestauranteIn restauranteIn);

}