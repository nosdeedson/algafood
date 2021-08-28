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
import com.ejs.algaworksCurso.api.model.in.cidade.CidadeIn;
import com.ejs.algaworksCurso.api.model.out.cidade.CidadeOut;
import com.ejs.algaworksCurso.api.openApi.controller.CidadeControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.CidadeService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path = "cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeService cidadeService;
	
	@Override
	@ApiOperation(value = "Atualiza cidade")
	@PutMapping("{id}")
	public ResponseEntity<StringUriResposta> atualizar(@PathVariable Long id, 
			@RequestBody @Valid CidadeIn cidadeIn){
		this.cidadeService.atualizar(cidadeIn, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		String url = "http://" + uri.getAuthority() + uri.getPath();
        StringUriResposta uriRetorno = new StringUriResposta(url);
		return ResponseEntity.ok(uriRetorno);
	}
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<CidadeOut> buscar(@PathVariable Long id){
		CidadeOut cidade = this.cidadeService.buscar(id);
		return ResponseEntity.ok(cidade);
	}
	
	@Override
	@GetMapping()
	public List<CidadeOut> listar(){
		return this.cidadeService.listar();
	}
	
	@Override
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		this.cidadeService.remover(id);	
	}
	
	@Override
	@PostMapping
	public ResponseEntity<StringUriResposta> salvar( @RequestBody @Valid CidadeIn cidadeIn){
			CidadeOut out = this.cidadeService.salvar(cidadeIn);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(out.getId())
					.toUri();
			String url = "http://" + uri.getAuthority() + uri.getPath();
	        StringUriResposta uriRetorno = new StringUriResposta(url);
			return ResponseEntity.status(HttpStatus.CREATED).body(uriRetorno);
	}
}
