package com.ejs.algaworksCurso.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<Void> associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
		this.userGrupoService.associar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("{grupoId}")
	public ResponseEntity<Void> desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
		this.userGrupoService.desassociar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<GrupoOut>> listar(@PathVariable Long usuarioId){
		CollectionModel<GrupoOut> grupos = this.userGrupoService.listar(usuarioId);
		grupos.getContent().stream().forEach( grupo ->{
			grupo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class)
					.desassociarGrupo(usuarioId, grupo.getId())).withRel("desassociar"));
			grupo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class)
					.associarGrupo(usuarioId, null)).withRel("associar"));
		});
		return ResponseEntity.ok(grupos);
	}

}
