package com.ejs.algaworksCurso.helper.formaPagamento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.in.FormaPagamentoIn;
import com.ejs.algaworksCurso.domain.model.FormaPagamento;

@Component
public class FormaPagamentoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamento formaPagamentoInToFormaPagamento(FormaPagamentoIn formaPagamentoIn) {
		return mapper.map(formaPagamentoIn, FormaPagamento.class);
	}
	
	public void formaPagamentoInToFormaPagamento( FormaPagamentoIn formaPagamentoIn, FormaPagamento formaPagamento) {
		mapper.map(formaPagamentoIn, formaPagamento);
	}
}
