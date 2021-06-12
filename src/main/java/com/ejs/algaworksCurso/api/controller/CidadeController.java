package com.ejs.algaworksCurso.api.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.domain.model.Cidade;
import com.ejs.algaworksCurso.domain.services.CidadeService;

@RestController
@RequestMapping("cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
			@RequestBody @Valid Cidade cidade){
		cidade = this.cidadeService.atualizar(cidade, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		return ResponseEntity.ok(uri);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		Cidade cidade = this.cidadeService.buscar(id);
		return ResponseEntity.ok(cidade);
	}
	
	@GetMapping()
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(this.cidadeService.listar());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		this.cidadeService.remover(id);	
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar( @RequestBody @Valid Cidade cidade){
			cidade = this.cidadeService.salvar(cidade);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(cidade.getId())
					.toUri();
			return ResponseEntity.status(HttpStatus.CREATED).body(uri);
	}
}
