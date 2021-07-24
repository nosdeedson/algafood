package com.ejs.algaworksCurso.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.domain.services.RestauranteUsuarioService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/usuarios")
public class RestauranteUsuarioController {
	
	@Autowired
	private RestauranteUsuarioService restauranteUsuarioService;
	
	@PutMapping("{usuarioId}")
	public ResponseEntity<?> associarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		this.restauranteUsuarioService.associarResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{usuarioId}")
	public ResponseEntity<?> desassociarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
		this.restauranteUsuarioService.desassociarResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<?> listarResponsaveis(@PathVariable Long restauranteId){
		List<UsuarioOut> usuarios = this.restauranteUsuarioService.listarResponsaveis(restauranteId);
		return ResponseEntity.ok(usuarios);
	}

}
