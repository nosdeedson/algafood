package com.ejs.algaworksCurso.api.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.api.model.in.produto.ProdutoIn;
import com.ejs.algaworksCurso.api.model.in.produto.ProdutoOut;
import com.ejs.algaworksCurso.domain.services.RestauranteProdutoService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
	
	@Autowired
	private RestauranteProdutoService restauranteProdutoService;
	
	@PutMapping("{produtoId}")
	public ResponseEntity<?> atualizarProduto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @RequestBody @Valid ProdutoIn produtoIn) {
		this.restauranteProdutoService.atualizarProduto(restauranteId, produtoId, produtoIn);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		return ResponseEntity.ok(uri);
	}
	
	@GetMapping("{produtoId}")
	public ResponseEntity<?> buscarProduto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId){
		ProdutoOut out = this.restauranteProdutoService.buscarProduto(restauranteId, produtoId);
		return ResponseEntity.ok(out);
	}
	
	@GetMapping()
	public ResponseEntity<?> listarProduto(@PathVariable Long restauranteId, 
			@RequestParam(required = false) boolean incluirInativos){
		List<ProdutoOut> produtos = this.restauranteProdutoService.listarProduto(restauranteId, incluirInativos);
		return ResponseEntity.ok(produtos);
	}
	
	@DeleteMapping("{produtoId}")
	public ResponseEntity<?> remover(@PathVariable Long restauranteId,
			@PathVariable Long produtoId){
		this.restauranteProdutoService.removerProduto(restauranteId, produtoId);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@PathVariable Long restauranteId,
			@RequestBody @Valid ProdutoIn produtoIn) {
		ProdutoOut out = this.restauranteProdutoService.salvarProduto(restauranteId, produtoIn);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(out.getId()).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(uri);
	}
	
	
}
