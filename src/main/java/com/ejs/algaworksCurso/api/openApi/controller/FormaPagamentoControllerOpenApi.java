package com.ejs.algaworksCurso.api.openApi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.ejs.algaworksCurso.api.model.in.formaPagamento.FormaPagamentoIn;
import com.ejs.algaworksCurso.api.model.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.api.openApi.model.FormasPagamentoModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Forma Pagamentos", value = "Gerencia formas pagamentos")
public interface FormaPagamentoControllerOpenApi {

	ResponseEntity<FormaPagamentoOut> autalizar(FormaPagamentoIn formaPagamentoIn, Long formaPagamentoId);

	ResponseEntity<FormaPagamentoOut> buscar(Long formaPagamentoId, ServletWebRequest request);

	@ApiOperation(value = "Lista as formas pagamento", response = FormasPagamentoModelOpenApi.class)
	ResponseEntity<CollectionModel<FormaPagamentoOut>> listar(ServletWebRequest request);

	void remover(Long formaPagamentoId);

	ResponseEntity<FormaPagamentoOut> salvar(FormaPagamentoIn formaPagamentoIn);

}