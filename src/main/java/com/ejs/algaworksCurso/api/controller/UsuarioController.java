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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.api.model.dto.in.senha.SenhaAtualizarIn;
import com.ejs.algaworksCurso.api.model.dto.in.usuario.UsuarioAtualizarIn;
import com.ejs.algaworksCurso.api.model.dto.in.usuario.UsuarioIn;
import com.ejs.algaworksCurso.api.model.dto.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.domain.services.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PutMapping("{usuarioId}")
	public ResponseEntity<?> atualizar(@PathVariable("usuarioId") Long usuarioId, 
			@RequestBody @Valid UsuarioAtualizarIn usuarioIn ){
		this.usuarioService.atualizar(usuarioIn, usuarioId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand().toUri();
		return ResponseEntity.ok(uri);
	}
	
	@PutMapping("atualizar-senha/{usuarioId}")
	public ResponseEntity<?> atualizarSemha(@RequestBody @Valid SenhaAtualizarIn senhaIn, 
			@PathVariable("usuarioId") Long usuarioId){
		this.usuarioService.atualizarSenha(senhaIn, usuarioId);
		return ResponseEntity.noContent().build();

	}
	
	@GetMapping("{usuarioId}")
	public ResponseEntity<?> buscar(@PathVariable("usuarioId") Long usuarioId){
		UsuarioOut usuarioOut = this.usuarioService.buscar(usuarioId);
		return ResponseEntity.ok(usuarioOut);
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<UsuarioOut> usuarios = this.usuarioService.listar();
		return ResponseEntity.ok(usuarios);
	}
	
	@DeleteMapping("{usuarioId}")
	public ResponseEntity<?> remover(@PathVariable("usuarioId") Long usuarioId){
		this.usuarioService.remover(usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid UsuarioIn usuarioIn){
		UsuarioOut usuarioOut = this.usuarioService.salvar(usuarioIn);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuarioOut.getId()).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(uri);
	}
	
	
	
	
	
	
	
	

}
