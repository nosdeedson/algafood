package com.ejs.algaworksCurso.api.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.api.model.in.cidade.CidadeIn;
import com.ejs.algaworksCurso.api.model.out.cidade.CidadeOut;
import com.ejs.algaworksCurso.api.openApi.controller.CidadeControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.CidadeService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path = "cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeService cidadeService;
	
	@Override
	@ApiOperation(value = "Atualiza cidade")
	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
			@RequestBody @Valid CidadeIn cidadeIn){
		this.cidadeService.atualizar(cidadeIn, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		return ResponseEntity.ok(uri);
	}
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		CidadeOut cidade = this.cidadeService.buscar(id);
		return ResponseEntity.ok(cidade);
	}
	
	@Override
	@GetMapping()
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(this.cidadeService.listar());
	}
	
	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		this.cidadeService.remover(id);	
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PostMapping
	public ResponseEntity<?> salvar( @RequestBody @Valid CidadeIn cidadeIn){
			CidadeOut out = this.cidadeService.salvar(cidadeIn);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(out.getId())
					.toUri();
			return ResponseEntity.status(HttpStatus.CREATED).body(uri);
	}
}
