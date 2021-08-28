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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.api.model.StringUriResposta;
import com.ejs.algaworksCurso.api.model.in.senha.SenhaAtualizarIn;
import com.ejs.algaworksCurso.api.model.in.usuario.UsuarioAtualizarIn;
import com.ejs.algaworksCurso.api.model.in.usuario.UsuarioIn;
import com.ejs.algaworksCurso.api.model.out.usuario.UsuarioOut;
import com.ejs.algaworksCurso.api.openApi.controller.UsuarioControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	@PutMapping("{usuarioId}")
	public ResponseEntity<StringUriResposta> atualizar(@PathVariable("usuarioId") Long usuarioId, 
			@RequestBody @Valid UsuarioAtualizarIn usuarioIn ){
		this.usuarioService.atualizar(usuarioIn, usuarioId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand().toUri();
		StringUriResposta url = new StringUriResposta("http://" + uri.getAuthority() + uri.getPath());
		return ResponseEntity.ok(url);
	}
	
	@Override
	@PutMapping("atualizar-senha/{usuarioId}")
	@ResponseStatus(code= HttpStatus.NO_CONTENT)
	public void atualizarSenha(@RequestBody @Valid SenhaAtualizarIn senhaIn, 
			@PathVariable("usuarioId") Long usuarioId){
		this.usuarioService.atualizarSenha(senhaIn, usuarioId);
	}
	
	@Override
	@GetMapping("{usuarioId}")
	public ResponseEntity<UsuarioOut> buscar(@PathVariable("usuarioId") Long usuarioId){
		UsuarioOut usuarioOut = this.usuarioService.buscar(usuarioId);
		return ResponseEntity.ok(usuarioOut);
	}
	
	@Override
	@GetMapping
	public ResponseEntity<List<UsuarioOut>> listar(){
		List<UsuarioOut> usuarios = this.usuarioService.listar();
		return ResponseEntity.ok(usuarios);
	}
	
	@Override
	@DeleteMapping("{usuarioId}")
	@ResponseStatus(code= HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("usuarioId") Long usuarioId){
		this.usuarioService.remover(usuarioId);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<StringUriResposta> salvar(@RequestBody @Valid UsuarioIn usuarioIn){
		UsuarioOut usuarioOut = this.usuarioService.salvar(usuarioIn);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuarioOut.getId()).toUri();
		StringUriResposta url = new StringUriResposta("http://" + uri.getAuthority() + uri.getPath());
		return ResponseEntity.status(HttpStatus.CREATED).body(url);
	}

}
