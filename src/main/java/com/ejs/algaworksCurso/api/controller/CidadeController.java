package com.ejs.algaworksCurso.api.controller;

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

import com.ejs.algaworksCurso.api.helper.ResourceUriHelper;
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
	public ResponseEntity<CidadeOut> atualizar(@PathVariable Long id, 
			@RequestBody @Valid CidadeIn cidadeIn){
		CidadeOut out =  this.cidadeService.atualizar(cidadeIn, id);
		ResourceUriHelper.addUriHeaderUpdate();
		return ResponseEntity.status(HttpStatus.CREATED).body(out);
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
	public ResponseEntity<CidadeOut> salvar( @RequestBody @Valid CidadeIn cidadeIn){
			CidadeOut out = this.cidadeService.salvar(cidadeIn);
			ResourceUriHelper.addUriHeaderSave(out.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}
}
