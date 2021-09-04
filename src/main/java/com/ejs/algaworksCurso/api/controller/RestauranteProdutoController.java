package com.ejs.algaworksCurso.api.controller;

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

import com.ejs.algaworksCurso.api.helper.ResourceUriHelper;
import com.ejs.algaworksCurso.api.model.in.produto.ProdutoIn;
import com.ejs.algaworksCurso.api.model.in.produto.ProdutoOut;
import com.ejs.algaworksCurso.api.openApi.controller.RestauranteProdutoControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.RestauranteProdutoService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {
	
	@Autowired
	private RestauranteProdutoService restauranteProdutoService;
	
	@Override
	@PutMapping("{produtoId}")
	public ResponseEntity<ProdutoOut> atualizarProduto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @RequestBody @Valid ProdutoIn produtoIn) {
		ProdutoOut out = this.restauranteProdutoService.atualizarProduto(restauranteId, produtoId, produtoIn);
		ResourceUriHelper.addUriHeaderUpdate();
		return ResponseEntity.ok(out);
	}
	
	@Override
	@GetMapping("{produtoId}")
	public ResponseEntity<ProdutoOut> buscarProduto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId){
		ProdutoOut out = this.restauranteProdutoService.buscarProduto(restauranteId, produtoId);
		return ResponseEntity.ok(out);
	}
	
	@Override
	@GetMapping()
	public ResponseEntity<List<ProdutoOut>> listarProduto(@PathVariable Long restauranteId, 
			@RequestParam(required = false) boolean incluirInativos){
		List<ProdutoOut> produtos = this.restauranteProdutoService.listarProduto(restauranteId, incluirInativos);
		return ResponseEntity.ok(produtos);
	}
	
	@Override
	@DeleteMapping("{produtoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId,
			@PathVariable Long produtoId){
		this.restauranteProdutoService.removerProduto(restauranteId, produtoId);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<ProdutoOut> salvar(@PathVariable Long restauranteId,
			@RequestBody @Valid ProdutoIn produtoIn) {
		ProdutoOut out = this.restauranteProdutoService.salvarProduto(restauranteId, produtoIn);
		ResourceUriHelper.addUriHeaderSave(out.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}
	
	
}
