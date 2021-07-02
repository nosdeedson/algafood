package com.ejs.algaworksCurso.api.controller;

import java.math.BigDecimal;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.api.model.dto.in.restaurante.RestauranteIn;
import com.ejs.algaworksCurso.api.model.dto.out.restautante.RestauranteOut;
import com.ejs.algaworksCurso.domain.exception.CidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.CozinhaNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.services.RestauranteService;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@PutMapping("{restauranteId}/aberto")
	public void abrir(@PathVariable Long restauranteId) {
		this.restauranteService.abrir(restauranteId);
	}
	
	@PutMapping("{restauranteId}/ativacao")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId){
		this.restauranteService.ativar(restauranteId);
	}
	
	@PutMapping("{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteIn restaurante){
		try {
			this.restauranteService.atualizar(restaurante, restauranteId);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
			return ResponseEntity.ok(uri);
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<?> buscar(@PathVariable Long restauranteId){
		return ResponseEntity.ok(this.restauranteService.buscar(restauranteId));
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
	
	@DeleteMapping("{restauranteId}/aberto")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
		this.restauranteService.fechar(restauranteId);
	}
	
	@GetMapping("encontrar-como")
	public ResponseEntity<?> find(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(required = false) BigDecimal taxaFreteInicial,
			@RequestParam(required = false) BigDecimal taxaFreteFinal){
		return ResponseEntity.ok(this.restauranteService.find(nome, taxaFreteInicial, taxaFreteFinal));
	}
	
	@DeleteMapping("{restauranteId}/ativacao")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId){
		this.restauranteService.inativar(restauranteId);
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(this.restauranteService.listar());
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid RestauranteIn restauranteIn){
		try {
			RestauranteOut restaurante = this.restauranteService.salvar(restauranteIn);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(restaurante.getId())
					.toUri();
			return ResponseEntity.status(HttpStatus.CREATED).body(uri);
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	

}
