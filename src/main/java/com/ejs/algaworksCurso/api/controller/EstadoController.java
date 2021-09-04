package com.ejs.algaworksCurso.api.controller;

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
import com.ejs.algaworksCurso.api.model.in.estado.EstadoIn;
import com.ejs.algaworksCurso.api.model.out.estado.EstadoOut;
import com.ejs.algaworksCurso.api.openApi.controller.EstadoControllerOpenApi;
import com.ejs.algaworksCurso.domain.services.EstadoService;

@RestController
@RequestMapping("estados")
public class EstadoController implements EstadoControllerOpenApi {
	
	@Autowired
	private EstadoService estadoService;
	
	@Override
	@PutMapping("{id}")
	public ResponseEntity<EstadoOut> atualizar(@PathVariable Long id,
			@RequestBody @Valid EstadoIn estadoIn){
			EstadoOut out = this.estadoService.atualizar(estadoIn, id);
			ResourceUriHelper.addUriHeaderUpdate();
			return ResponseEntity.ok(out);
	}
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<EstadoOut> buscar(@PathVariable Long id){
		EstadoOut estado = this.estadoService.buscar(id);
		return ResponseEntity.ok(estado);
	}
	
	@Override
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<EstadoOut> listar(){
		List<EstadoOut> estados = this.estadoService.listar();			
		return estados;
	}
	
	@Override
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		this.estadoService.remover(id);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<EstadoOut> salvar(@RequestBody @Valid EstadoIn estadoIn){
		EstadoOut estado = this.estadoService.salvar(estadoIn);
		ResourceUriHelper.addUriHeaderSave(estado.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}

}
