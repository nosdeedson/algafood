package com.ejs.algaworksCurso.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.model.in.produto.FotoProdutoIn;
import com.ejs.algaworksCurso.api.model.out.fotoProduto.FotoProdutoOut;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.services.FotoProdutoService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteFotoProdutoContoller {

	@Autowired
	FotoProdutoService fotoProdutoService;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> atualizarFoto(@PathVariable("restauranteId") Long restauranteId,
		@PathVariable("produtoId") Long produtoId, @Valid FotoProdutoIn fotoProdutoIn ) throws IOException{
	
		try {
			FotoProdutoOut out = this.fotoProdutoService.salvar(fotoProdutoIn, restauranteId, produtoId);		
			return ResponseEntity.ok(out);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException("Cada produto pode ter apenas uma foto.");
		}
	}
	
	
}
