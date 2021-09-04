package com.ejs.algaworksCurso.api.openApi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.ejs.algaworksCurso.api.model.in.formaPagamento.FormaPagamentoIn;
import com.ejs.algaworksCurso.api.model.out.formaPagamento.FormaPagamentoOut;

import io.swagger.annotations.Api;

@Api(tags = "Forma Pagamentos", value = "Gerencia formas pagamentos")
public interface FormaPagamentoControllerOpenApi {

	ResponseEntity<FormaPagamentoOut> autalizar(FormaPagamentoIn formaPagamentoIn, Long formaPagamentoId);

	ResponseEntity<FormaPagamentoOut> buscar(Long formaPagamentoId, ServletWebRequest request);

	ResponseEntity<List<FormaPagamentoOut>> listar(ServletWebRequest request);

	void remover(Long formaPagamentoId);

	ResponseEntity<FormaPagamentoOut> salvar(FormaPagamentoIn formaPagamentoIn);

}