package com.ejs.algaworksCurso.api.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.ejs.algaworksCurso.domain.services.RestauranteService;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			restaurante = this.restauranteService.adicionar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch( EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
			@RequestBody Restaurante restaurante){
		try {
			restaurante = this.restauranteService.atualizar(restaurante, restauranteId);
			return ResponseEntity.ok(restaurante);
		} catch ( EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id,
			@RequestBody Map<String, Object>  dadosAtualizar){
		try {
			Restaurante atualizado = this.restauranteService.atualizarParcial(dadosAtualizar, id);
			return ResponseEntity.status(HttpStatus.OK).body(atualizado);
		} catch ( EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<?> buscar(@PathVariable Long restauranteId){
		try {			
			return ResponseEntity.ok(this.restauranteService.buscar(restauranteId));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("com-frete-gratis")
	public ResponseEntity<?> encontrarComFreteGratis(@RequestParam(name = "nome", required = true) String nome){
		/* Exemplo com classes*/
		//return ResponseEntity.ok(this.restauranteService.encontrarComFreteGratis(nome));
		/*Exemplo com a fábrica de specs*/
		
		return ResponseEntity.ok(this.restauranteService.encontrarComFreteGratis(nome));
	}
	
	@GetMapping("encocntrar-primeiro")
	public ResponseEntity<?> encontrarPrimeiro(){
		return ResponseEntity.ok(this.restauranteService.encontrarPrimeiro());
	}
	
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(this.restauranteService.listar());
	}
	
	@GetMapping("encontrar-como")
	public ResponseEntity<?> find(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(required = false) BigDecimal taxaFreteInicial,
			@RequestParam(required = false) BigDecimal taxaFreteFinal){
		return ResponseEntity.ok(this.restauranteService.find(nome, taxaFreteInicial, taxaFreteFinal));
	}

}
