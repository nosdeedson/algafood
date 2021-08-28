package com.ejs.algaworksCurso.api.controller;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ejs.algaworksCurso.api.model.StringUriResposta;
import com.ejs.algaworksCurso.api.model.in.formaPagamento.FormaPagamentoIn;
import com.ejs.algaworksCurso.api.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.api.openApi.controller.FormaPagamentoControllerOpenApi;
import com.ejs.algaworksCurso.domain.repository.FormaPagamentoRepository;
import com.ejs.algaworksCurso.domain.services.FormaPagamentoService;

@RestController
@RequestMapping("forma-pagamentos")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Override
	@PutMapping("{formaPagamentoId}")
	public ResponseEntity<StringUriResposta> autalizar(@RequestBody @Valid FormaPagamentoIn formaPagamentoIn,
			@PathVariable(name = "formaPagamentoId") Long formaPagamentoId ){
		this.formaPagamentoService.atualizar(formaPagamentoIn, formaPagamentoId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand().toUri();
		StringUriResposta urlResposta =  new StringUriResposta("http://" + uri.getAuthority() + uri.getPath());
		return ResponseEntity.ok(urlResposta);
	}
	
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
	
	@Override
	@GetMapping
	public ResponseEntity<List<FormaPagamentoOut>> listar( ServletWebRequest request ){
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		OffsetDateTime ultimaDataAtualizacao = this.formaPagamentoRepository.ultimaDataAtualizacao();
		
		String eTag = "0";
		if ( ultimaDataAtualizacao != null) {
			eTag = String.valueOf(ultimaDataAtualizacao.toEpochSecond());
		}
		if( request.checkNotModified(eTag)) {
			return null;
		}
		
		List<FormaPagamentoOut> formasPagamentos = this.formaPagamentoService.listar();
		return ResponseEntity
				.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formasPagamentos);
	}
	
	
	@Override
	@DeleteMapping("{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId){
		this.formaPagamentoService.remover(formaPagamentoId);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<StringUriResposta> salvar(@RequestBody @Valid FormaPagamentoIn formaPagamentoIn){
		FormaPagamentoOut formaPagamentoOut = this.formaPagamentoService.salvar(formaPagamentoIn);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{formaPagamentoId}")
				.buildAndExpand(formaPagamentoOut.getId())
				.toUri();
		StringUriResposta url = new StringUriResposta("httl://" + uri.getAuthority() + uri.getPath());
		return ResponseEntity.status(HttpStatus.CREATED).body(url);
	}
	
}
