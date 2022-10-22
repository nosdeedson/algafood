package com.ejs.algaworksCurso.api.v1.openApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.ejs.algaworksCurso.api.v1.model.in.formaPagamento.FormaPagamentoIn;
import com.ejs.algaworksCurso.api.v1.model.out.formaPagamento.FormaPagamentoOut;

@Tag(name = "Formas Pagamento")
@SecurityRequirement(name = "bearer-token")
public interface FormaPagamentoControllerOpenApi {

	ResponseEntity<FormaPagamentoOut> autalizar(FormaPagamentoIn formaPagamentoIn, Long formaPagamentoId);

	ResponseEntity<FormaPagamentoOut> buscar(Long formaPagamentoId, ServletWebRequest request);

	ResponseEntity<CollectionModel<FormaPagamentoOut>> listar(ServletWebRequest request);

	void remover(Long formaPagamentoId);

	ResponseEntity<FormaPagamentoOut> salvar(FormaPagamentoIn formaPagamentoIn);

}