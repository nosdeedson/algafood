package com.ejs.algaworksCurso.domain.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejs.algaworksCurso.api.model.dto.in.produto.ProdutoIn;
import com.ejs.algaworksCurso.api.model.dto.in.produto.ProdutoOut;
import com.ejs.algaworksCurso.domain.exception.ProdutoNaoEncontradoException;
import com.ejs.algaworksCurso.domain.model.Produto;
import com.ejs.algaworksCurso.domain.model.Restaurante;
import com.ejs.algaworksCurso.domain.repository.ProdutoRepository;
import com.ejs.algaworksCurso.helper.produto.ProdutoAssembler;
import com.ejs.algaworksCurso.helper.produto.ProdutoDisAssembler;

@Service
public class RestauranteProdutoService {

	@Autowired
	private ProdutoAssembler produtoAssembler;
	
	@Autowired
	private ProdutoDisAssembler produtoDisAssembler;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
		
	@Transactional
	public void atualizarProduto( Long restauranteId, Long ProdutoId, ProdutoIn produtoIn) {
		this.restauranteService.buscarOuFalhar(restauranteId);
		Produto produto = this.produtoRepository.findByIdAndRestaurante_Id(ProdutoId, restauranteId)
				.orElseThrow( () -> new ProdutoNaoEncontradoException(ProdutoId, restauranteId) );
		
		this.produtoAssembler.produtoInToProduto(produtoIn, produto);
		this.produtoRepository.save(produto);		
	}
	
	public ProdutoOut buscarProduto(Long restauranteId, Long produtoId) {
		Produto produto = this.produtoRepository.findByIdAndRestaurante_Id(produtoId, restauranteId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId, restauranteId));
		return this.produtoDisAssembler.produtoToProdutoOUt(produto);
	}
	
	public List<ProdutoOut> listarProduto(Long restauranteId){
		Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
		return restaurante.getProdutos().stream()
					.map(produto -> this.produtoDisAssembler.produtoToProdutoOUt(produto))
					.collect(Collectors.toList());
	}
	
	@Transactional
	public void removerProduto(Long restauranteId, Long produtoId) {
		Produto produto = this.produtoRepository.findByIdAndRestaurante_Id(produtoId, restauranteId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId, restauranteId));
		Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
		restaurante.getProdutos().remove(produto);
		this.produtoRepository.delete(produto);
	}
	
	@Transactional
	public ProdutoOut salvarProduto(Long restauranteId, ProdutoIn produtoIn) {
		Restaurante restaurante = this.restauranteService.buscarOuFalhar(restauranteId);
		Produto produto = this.produtoAssembler.produtoInToProduto(produtoIn);
		produto.setRestaurante(restaurante);
		produto = this.produtoRepository.save(produto);
		restaurante.setProdutos(Arrays.asList(produto));
		return this.produtoDisAssembler.produtoToProdutoOUt(produto);
	}
}
