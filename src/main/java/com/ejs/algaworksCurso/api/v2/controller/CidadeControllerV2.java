package com.ejs.algaworksCurso.api.v2.controller;

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

import com.ejs.algaworksCurso.api.helper.ResourceUriHelper;
import com.ejs.algaworksCurso.api.v2.model.in.cidade.CidadeInV2;
import com.ejs.algaworksCurso.api.v2.model.openApi.CidadeControllerOpenApiV2;
import com.ejs.algaworksCurso.api.v2.model.out.cidade.CidadeOutV2;
import com.ejs.algaworksCurso.core.web.AlgaMediaType;
import com.ejs.algaworksCurso.domain.services.v2.CidadeServiceV2;


@RestController
@RequestMapping(path = "v2/cidades", produces = AlgaMediaType.V2_APLICATION_JSON_VALUE)
public class CidadeControllerV2 implements CidadeControllerOpenApiV2{

	@Autowired
	private CidadeServiceV2 cidadeServiceV2;
	
	@Override
	@PutMapping("{id}")
	public ResponseEntity<CidadeOutV2> atualizar(@PathVariable Long id, 
			@RequestBody @Valid CidadeInV2 cidadeIn){
		CidadeOutV2 out =  this.cidadeServiceV2.atualizar(cidadeIn, id);
		ResourceUriHelper.addUriHeaderUpdate();
		return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<CidadeOutV2> buscar(@PathVariable Long id){
		CidadeOutV2 cidade = this.cidadeServiceV2.buscar(id);
		return ResponseEntity.ok(cidade);
	}
	
	@Override
	@GetMapping
	public List<CidadeOutV2> listar(){
				
		List<CidadeOutV2> cidadesModel = this.cidadeServiceV2.listar();
		
		return cidadesModel;
	}
	
	@Override
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		this.cidadeServiceV2.remover(id);	
	}
	
	@Override
	@PostMapping
	public ResponseEntity<CidadeOutV2> salvar( @RequestBody @Valid CidadeInV2 cidadeIn){
			CidadeOutV2 out = this.cidadeServiceV2.salvar(cidadeIn);
			ResourceUriHelper.addUriHeaderSave(out.getIdCidade());
			return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}
}
