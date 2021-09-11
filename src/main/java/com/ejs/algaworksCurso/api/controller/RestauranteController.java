package com.ejs.algaworksCurso.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.ejs.algaworksCurso.api.helper.ResourceUriHelper;
import com.ejs.algaworksCurso.api.model.in.restaurante.RestauranteIn;
import com.ejs.algaworksCurso.api.model.out.restautante.RestauranteOut;
import com.ejs.algaworksCurso.api.openApi.controller.RestauranteControllerOpenApin;
import com.ejs.algaworksCurso.domain.exception.CidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.CozinhaNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.exception.RestauranteNaoEncontradoException;
import com.ejs.algaworksCurso.domain.services.RestauranteService;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController implements RestauranteControllerOpenApin {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Override
	@PutMapping("{restauranteId}/aberto")
	public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
		this.restauranteService.abrir(restauranteId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("{restauranteId}/ativacao")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId){
		this.restauranteService.ativar(restauranteId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("ativacoes")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			this.restauranteService.ativarMultiplos(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@Override
	@PutMapping("{restauranteId}")
	public ResponseEntity<RestauranteOut> atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteIn restaurante){
		try {
			RestauranteOut out = this.restauranteService.atualizar(restaurante, restauranteId);
			ResourceUriHelper.addUriHeaderUpdate();
			return ResponseEntity.ok(out);
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@Override
	@GetMapping("/{restauranteId}")
	public ResponseEntity<RestauranteOut> buscar(@PathVariable Long restauranteId){
		return ResponseEntity.ok(this.restauranteService.buscar(restauranteId));
	}
	
	@Override
	@GetMapping("com-frete-gratis")
	public ResponseEntity<List<RestauranteOut>> encontrarComFreteGratis(@RequestParam(name = "nome", required = true) String nome){
		/* Exemplo com classes*/
		//return ResponseEntity.ok(this.restauranteService.encontrarComFreteGratis(nome));
		/*Exemplo com a fábrica de specs*/
		
		return ResponseEntity.ok(this.restauranteService.encontrarComFreteGratis(nome));
	}
	
	@Override
	@GetMapping("encocntrar-primeiro")
	public ResponseEntity<RestauranteOut> encontrarPrimeiro(){
		return ResponseEntity.ok(this.restauranteService.encontrarPrimeiro());
	}
	
	@Override
	@DeleteMapping("{restauranteId}/aberto")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
		this.restauranteService.fechar(restauranteId);
		return ResponseEntity.noContent().build();
	}
	
	
	@Override
	@DeleteMapping("{restauranteId}/ativacao")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId){
		this.restauranteService.inativar(restauranteId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("inativacoes")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds){
		try {
			this.restauranteService.inativarMultiplos(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<RestauranteOut>> listar(){
		return ResponseEntity.ok(this.restauranteService.listar());
	}
	

	@Override
	@GetMapping("encontrar-como")
	public ResponseEntity<CollectionModel<RestauranteOut>> listar(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(required = false) BigDecimal taxaFreteInicial,
			@RequestParam(required = false) BigDecimal taxaFreteFinal){
		return ResponseEntity.ok(this.restauranteService.listar(nome, taxaFreteInicial, taxaFreteFinal));
	}
		
	@Override
	@PostMapping
	public ResponseEntity<RestauranteOut> salvar(@RequestBody @Valid RestauranteIn restauranteIn){
		try {
			RestauranteOut restaurante = this.restauranteService.salvar(restauranteIn);
			ResourceUriHelper.addUriHeaderSave(restaurante.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	

}
