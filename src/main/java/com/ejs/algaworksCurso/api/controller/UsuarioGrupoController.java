package com.ejs.algaworksCurso.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.model.out.group.GrupoOut;
import com.ejs.algaworksCurso.api.openApi.controller.UsuarioGrupoControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.UsuarioGrupoService;

@RestController
@RequestMapping("usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {
	
	@Autowired
	private UsuarioGrupoService userGrupoService;
	
	@Override
	@PutMapping("{grupoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
		this.userGrupoService.associar(usuarioId, grupoId);
	}
	
	@Override
	@DeleteMapping("{grupoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
		this.userGrupoService.desassociar(usuarioId, grupoId);
	}
	
	@Override
	@GetMapping
	public ResponseEntity<List<GrupoOut> > listar(@PathVariable Long usuarioId){
		List<GrupoOut> grupos = this.userGrupoService.listar(usuarioId);
		return ResponseEntity.ok(grupos);
	}

}
