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

import com.ejs.algaworksCurso.api.model.out.group.GrupoOut;
import com.ejs.algaworksCurso.domain.services.UsuarioGrupoService;

@RestController
@RequestMapping("usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	
	@Autowired
	private UsuarioGrupoService userGrupoService;
	
	@PutMapping("{grupoId}")
	public ResponseEntity<?> associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
		this.userGrupoService.associar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{grupoId}")
	public ResponseEntity<?> desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
		this.userGrupoService.desassociar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<?> listar(@PathVariable Long usuarioId){
		List<GrupoOut> grupos = this.userGrupoService.listar(usuarioId);
		return ResponseEntity.ok(grupos);
	}

}
