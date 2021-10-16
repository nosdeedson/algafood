package com.ejs.algaworksCurso.api.v1.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ejs.algaworksCurso.api.v1.model.in.cidade.CidadeIn;
import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.CidadeControllerOpenApi;
import com.ejs.algaworksCurso.core.security.CheckSecurity;
import com.ejs.algaworksCurso.core.web.AlgaMediaType;
import com.ejs.algaworksCurso.domain.services.CidadeService;


@RestController
@RequestMapping(path = "cidades", produces = AlgaMediaType.V1_APLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	private final Logger logger = LoggerFactory.getLogger(CozinhaController.class);
	
	@Autowired
	private CidadeService cidadeService;
	
	@CheckSecurity.Cidades.PodeEditar
	@Override
	@PutMapping("{id}")
	public ResponseEntity<CidadeOut> atualizar(@PathVariable Long id, 
			@RequestBody @Valid CidadeIn cidadeIn){
		CidadeOut out =  this.cidadeService.atualizar(cidadeIn, id);
		ResourceUriHelper.addUriHeaderUpdate();
		return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}
	
	@CheckSecurity.Cidades.PodeConsultar
	@Override
	@GetMapping("{id}")
	public ResponseEntity<CidadeOut> buscar(@PathVariable Long id){
		CidadeOut cidade = this.cidadeService.buscar(id);
//		// criei o método em ResourceUriHelper que devolve a uri
//		cidade.add(Link.of(ResourceUriHelper.uriRequest(null)));
//		
//		// usa classe do próprio hateos do spring
//		cidade.add(WebMvcLinkBuilder.linkTo(CidadeControllerV2.class).withRel("cidades"));
//		cidade.getEstado().add(Link.of(ResourceUriHelper.uriRequest(null)));
		return ResponseEntity.ok(cidade);
	}

	@CheckSecurity.Cidades.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<CidadeOut> listar(){
		logger.info("listanto cozinhas");
		
		CollectionModel<CidadeOut> cidadesModel = this.cidadeService.listar();
		
		return cidadesModel;
	}
	
	@CheckSecurity.Cidades.PodeEditar
	@Override
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		this.cidadeService.remover(id);	
	}
	
	@CheckSecurity.Cidades.PodeEditar
	@Override
	@PostMapping
	public ResponseEntity<CidadeOut> salvar( @RequestBody @Valid CidadeIn cidadeIn){
			CidadeOut out = this.cidadeService.salvar(cidadeIn);
			ResourceUriHelper.addUriHeaderSave(out.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(out);
	}
}
