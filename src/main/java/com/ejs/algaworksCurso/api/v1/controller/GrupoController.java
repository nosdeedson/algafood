package com.ejs.algaworksCurso.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ejs.algaworksCurso.api.helper.ResourceUriHelper;
import com.ejs.algaworksCurso.api.v1.model.in.grupo.GrupoIn;
import com.ejs.algaworksCurso.api.v1.model.in.grupo.GrupoPermissoesIn;
import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.GrupoControllerOpenApi;
import com.ejs.algaworksCurso.core.security.CheckSecurity;
import com.ejs.algaworksCurso.domain.services.GrupoService;

@RestController
@RequestMapping(path = "grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoService grupoService;
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@PutMapping("{grupoId}")
	public ResponseEntity<GrupoOut> atualizar(@RequestBody @Valid GrupoIn grupoIn,
			@PathVariable("grupoId") Long grupoId){
		GrupoOut out = this.grupoService.atualizar(grupoIn, grupoId);
		ResourceUriHelper.addUriHeaderUpdate();
		return ResponseEntity.ok(out);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeConsultar
	@Override
	@GetMapping("{grupoId}")
	public ResponseEntity<GrupoOut> buscar(@PathVariable("grupoId") Long grupoId){
		GrupoOut grupoOut = this.grupoService.buscar(grupoId);
		return ResponseEntity.ok(grupoOut);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeConsultar
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<GrupoOut>> listar(){
		CollectionModel<GrupoOut> gruposOut = this.grupoService.listar();
		return ResponseEntity.ok(gruposOut);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeConsultar
	@Override
	@GetMapping("usuarios-por-grupo/{grupoId}")
	public ResponseEntity<CollectionModel<UsuarioOut>> listarUsuariosPorGrupo(@PathVariable Long grupoId) {
		CollectionModel<UsuarioOut> out = this.grupoService.listarUsuariosPorGrupo(grupoId);
		return ResponseEntity.ok(out);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@DeleteMapping("{grupoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("grupoId") Long grupoId){
		this.grupoService.remover(grupoId);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@PostMapping
	public ResponseEntity<GrupoOut> salvar(@RequestBody @Valid GrupoIn grupoIn){
		GrupoOut grupoOut = this.grupoService.salvar(grupoIn);
		ResourceUriHelper.addUriHeaderSave(grupoOut.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(grupoOut);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@PostMapping("salvar-grupo-associar-permissoes")
	public ResponseEntity<GrupoOut> salvarGrupoAssociarPermissoes(
			@RequestBody GrupoPermissoesIn in) {
		GrupoOut out = this.grupoService.salvarGrupoAssociarPermissoes(in);
		ResourceUriHelper.addUriHeaderSave(out.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}
	
}
