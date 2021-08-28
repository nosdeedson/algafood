package com.ejs.algaworksCurso.api.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.api.model.StringUriResposta;
import com.ejs.algaworksCurso.api.model.in.grupo.GrupoIn;
import com.ejs.algaworksCurso.api.model.out.group.GrupoOut;
import com.ejs.algaworksCurso.api.openApi.controller.GrupoControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.GrupoService;

@RestController
@RequestMapping(path = "grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoService grupoService;
	
	@Override
	@PutMapping("{grupoId}")
	public ResponseEntity<StringUriResposta> atualizar(@RequestBody @Valid GrupoIn grupoIn,
			@PathVariable("grupoId") Long grupoId){
		this.grupoService.atualizar(grupoIn, grupoId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		StringUriResposta url = new StringUriResposta("http://" + uri.getAuthority() + uri.getPath());
		return ResponseEntity.ok(url);
	}
	
	@Override
	@GetMapping("{grupoId}")
	public ResponseEntity<GrupoOut> buscar(@PathVariable("grupoId") Long grupoId){
		GrupoOut grupoOut = this.grupoService.buscar(grupoId);
		return ResponseEntity.ok(grupoOut);
	}
	
	@Override
	@GetMapping
	public ResponseEntity<List<GrupoOut>> listar(){
		List<GrupoOut> gruposOut = this.grupoService.listar();
		return ResponseEntity.ok(gruposOut);
	}
	
	@Override
	@DeleteMapping("{grupoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("grupoId") Long grupoId){
		this.grupoService.remover(grupoId);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<StringUriResposta> salvar(@RequestBody @Valid GrupoIn grupoIn){
		GrupoOut grupoOut = this.grupoService.salvar(grupoIn);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(grupoOut.getId()).toUri();
		StringUriResposta url = new StringUriResposta("http://"+ uri.getAuthority() + uri.getPath());
		return ResponseEntity.status(HttpStatus.CREATED).body(url);
	}
	
}
