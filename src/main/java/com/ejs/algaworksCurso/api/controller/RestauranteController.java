package com.ejs.algaworksCurso.api.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

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

import com.ejs.algaworksCurso.api.model.StringUriResposta;
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
	public void abrir(@PathVariable Long restauranteId) {
		this.restauranteService.abrir(restauranteId);
	}
	
	@Override
	@PutMapping("{restauranteId}/ativacao")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId){
		this.restauranteService.ativar(restauranteId);
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
	public ResponseEntity<StringUriResposta> atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteIn restaurante){
		try {
			this.restauranteService.atualizar(restaurante, restauranteId);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
			StringUriResposta url = new StringUriResposta("http://" + uri.getAuthority() + uri.getPath());
			return ResponseEntity.ok(url);
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
	public void fechar(@PathVariable Long restauranteId) {
		this.restauranteService.fechar(restauranteId);
	}
	
	@Override
	@GetMapping("encontrar-como")
	public ResponseEntity<List<RestauranteOut>> listar(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(required = false) BigDecimal taxaFreteInicial,
			@RequestParam(required = false) BigDecimal taxaFreteFinal){
		return ResponseEntity.ok(this.restauranteService.listar(nome, taxaFreteInicial, taxaFreteFinal));
	}
	
	@Override
	@DeleteMapping("{restauranteId}/ativacao")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId){
		this.restauranteService.inativar(restauranteId);
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
	public ResponseEntity<List<RestauranteOut>> listar(){
		return ResponseEntity.ok(this.restauranteService.listar());
	}
		
	@Override
	@PostMapping
	public ResponseEntity<StringUriResposta> salvar(@RequestBody @Valid RestauranteIn restauranteIn){
		try {
			RestauranteOut restaurante = this.restauranteService.salvar(restauranteIn);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(restaurante.getId())
					.toUri();
			StringUriResposta url = new StringUriResposta("http://" + uri.getAuthority() + uri.getPath());
			return ResponseEntity.status(HttpStatus.CREATED).body(url);
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	

}
