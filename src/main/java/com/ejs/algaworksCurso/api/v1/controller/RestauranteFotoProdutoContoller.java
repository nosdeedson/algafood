package com.ejs.algaworksCurso.api.v1.controller;

import java.io.IOException;

import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ejs.algaworksCurso.api.v1.model.in.produto.FotoProdutoIn;
import com.ejs.algaworksCurso.api.v1.model.out.fotoProduto.FotoProdutoOut;
import com.ejs.algaworksCurso.api.v1.model.out.fotoProduto.ImagemOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.RestauranteFotoProdutoControllerOpenApi;
import com.ejs.algaworksCurso.core.security.CheckSecurity;
import com.ejs.algaworksCurso.domain.exception.EntidadeNaoEncontradaException;
import com.ejs.algaworksCurso.domain.exception.NegocioException;
import com.ejs.algaworksCurso.domain.services.FotoProdutoService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteFotoProdutoContoller implements RestauranteFotoProdutoControllerOpenApi {

	@Autowired
	FotoProdutoService fotoProdutoService;
	
	@CheckSecurity.Restaurantes.PodeEditar
	@Override
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FotoProdutoOut> atualizarFoto(@PathVariable("restauranteId") Long restauranteId,
		@PathVariable("produtoId") Long produtoId, @Valid FotoProdutoIn fotoProdutoIn) throws IOException{
	
		try {
			FotoProdutoOut out = this.fotoProdutoService.salvar(fotoProdutoIn, restauranteId, produtoId);		
			return ResponseEntity.ok(out);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException("Cada produto pode ter apenas uma foto.");
		}
	}
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@Override
	@GetMapping
	public ResponseEntity<FotoProdutoOut> buscarDadosFoto(@PathVariable("restauranteId") Long restauranteId,
			@PathVariable("produtoId") Long produtoId){
		FotoProdutoOut out = this.fotoProdutoService.recuperar(restauranteId, produtoId);
		return ResponseEntity.ok(out);
	}
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@Override
	@GetMapping( path = "imagem" )
	public ResponseEntity<?> buscarFoto(@PathVariable("restauranteId") Long restauranteId,
			@PathVariable("produtoId") Long produtoId, @RequestHeader(value =  "accept", required = false) String acceptType) throws HttpMediaTypeNotAcceptableException{
		try {
						
			ImagemOut imgOut = this.fotoProdutoService.recuperarFoto(restauranteId, produtoId, acceptType);
			
			if ( imgOut.getFotoRecuperada().temUrl()) {
				return ResponseEntity.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, imgOut.getFotoRecuperada().getUrl())
						.build();
			}else {
				return ResponseEntity
						.ok()
						.contentType(imgOut.getMediaType())
						.body(new InputStreamResource(imgOut.getFotoRecuperada().getInputStream()));				
			}
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@Override
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletarFotoProduto(@PathVariable("restauranteId") Long restauranteId,
			@PathVariable("produtoId") Long produtoId){
		this.fotoProdutoService.deletarFotoProduto(restauranteId, produtoId);		
	}

	
}
