package com.ejs.algaworksCurso.api.v1.controller;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
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
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.ejs.algaworksCurso.api.helper.ResourceUriHelper;
import com.ejs.algaworksCurso.api.v1.model.in.formaPagamento.FormaPagamentoIn;
import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.api.v1.openApi.controller.FormaPagamentoControllerOpenApi;
import com.ejs.algaworksCurso.core.security.CheckSecurity;
import com.ejs.algaworksCurso.domain.repository.FormaPagamentoRepository;
import com.ejs.algaworksCurso.domain.services.FormaPagamentoService;

@RestController
@RequestMapping("forma-pagamentos")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@Override
	@PutMapping("{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoOut> autalizar(@RequestBody @Valid FormaPagamentoIn formaPagamentoIn,
			@PathVariable(name = "formaPagamentoId") Long formaPagamentoId ){
		FormaPagamentoOut out = this.formaPagamentoService.atualizar(formaPagamentoIn, formaPagamentoId);
		return ResponseEntity.ok(out);
	}
	
	@CheckSecurity.FormasPagamento.PodeConsultar
	@Override
	@GetMapping("{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoOut> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		OffsetDateTime ultimaDataAtualizacao = this.formaPagamentoRepository.ultimaDataAtualizacao();
		String eTag = "0";
		
		if ( ultimaDataAtualizacao != null) {
			eTag = String.valueOf(ultimaDataAtualizacao.toEpochSecond());
		}
		if ( request.checkNotModified(eTag)) {
			return null;
		}
		
		FormaPagamentoOut out = formaPagamentoService.buscar(formaPagamentoId);
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(15, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(out);
	}
	
	@CheckSecurity.FormasPagamento.PodeConsultar
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<FormaPagamentoOut>> listar( ServletWebRequest request ){
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		OffsetDateTime ultimaDataAtualizacao = this.formaPagamentoRepository.ultimaDataAtualizacao();
		
		String eTag = "0";
		if ( ultimaDataAtualizacao != null) {
			eTag = String.valueOf(ultimaDataAtualizacao.toEpochSecond());
		}
		if( request.checkNotModified(eTag)) {
			return null;
		}
		
		CollectionModel<FormaPagamentoOut> formasPagamentos = this.formaPagamentoService.listar();
		return ResponseEntity
				.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formasPagamentos);
	}
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@Override
	@DeleteMapping("{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId){
		this.formaPagamentoService.remover(formaPagamentoId);
	}
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@Override
	@PostMapping
	public ResponseEntity<FormaPagamentoOut> salvar(@RequestBody @Valid FormaPagamentoIn formaPagamentoIn){
		FormaPagamentoOut formaPagamentoOut = this.formaPagamentoService.salvar(formaPagamentoIn);
		ResourceUriHelper.addUriHeaderSave(formaPagamentoOut.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoOut);
	}
	
}
