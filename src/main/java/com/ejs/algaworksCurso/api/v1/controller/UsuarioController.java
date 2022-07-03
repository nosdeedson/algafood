package com.ejs.algaworksCurso.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
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
import com.ejs.algaworksCurso.api.v1.model.in.senha.SenhaAtualizarIn;
import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioAtualizarIn;
import com.ejs.algaworksCurso.api.v1.model.in.usuario.UsuarioIn;
import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.UsuarioControllerOpenApi;
import com.ejs.algaworksCurso.core.security.CheckSecurity;
import com.ejs.algaworksCurso.domain.services.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeEditarUsuario
	@Override
	@PutMapping("{usuarioId}")
	public ResponseEntity<UsuarioOut> atualizar(@PathVariable("usuarioId") Long usuarioId, 
			@RequestBody @Valid UsuarioAtualizarIn usuarioIn ){
		UsuarioOut out = this.usuarioService.atualizar(usuarioIn, usuarioId);
		ResourceUriHelper.addUriHeaderUpdate();
		return ResponseEntity.ok(out);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeAlterarSenha
	@Override
	@PutMapping("atualizar-senha/{usuarioId}")
	@ResponseStatus(code= HttpStatus.NO_CONTENT)
	public void atualizarSenha(@RequestBody @Valid SenhaAtualizarIn senhaIn, 
			@PathVariable("usuarioId") Long usuarioId){
		this.usuarioService.atualizarSenha(senhaIn, usuarioId);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeAlterarSenha
	@Override
	@GetMapping("{usuarioId}")
	public ResponseEntity<UsuarioOut> buscar(@PathVariable("usuarioId") Long usuarioId){
		UsuarioOut usuarioOut = this.usuarioService.buscar(usuarioId);
		return ResponseEntity.ok(usuarioOut);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeConsultar
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<UsuarioOut>> listar(){
		CollectionModel<UsuarioOut> usuarios = this.usuarioService.listar();
		return ResponseEntity.ok(usuarios);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeConsultar
	@Override
	@GetMapping("usuarios-sem-vinculo-grupo/{grupoId}")
	public ResponseEntity<CollectionModel<UsuarioOut>> listarUsuariosNãoVinculadosAoGrupo(
			@PathVariable("grupoId") Long grupoId) {
		CollectionModel<UsuarioOut> usuarios = this.usuarioService.listarUsuariosNãoVinculadosAoGrupo(grupoId);
		return ResponseEntity.ok(usuarios);
	}
	
	@CheckSecurity.GruposPermissoesUsuarios.PodeEditar
	@Override
	@DeleteMapping("{usuarioId}")
	@ResponseStatus(code= HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("usuarioId") Long usuarioId){
		this.usuarioService.remover(usuarioId);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<UsuarioOut> salvar(@RequestBody @Valid UsuarioIn usuarioIn){
		UsuarioOut usuarioOut = this.usuarioService.salvar(usuarioIn);
		ResourceUriHelper.addUriHeaderSave(usuarioOut.getId()); 
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioOut);
	}

}
