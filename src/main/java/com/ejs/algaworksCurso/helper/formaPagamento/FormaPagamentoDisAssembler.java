package com.ejs.algaworksCurso.helper.formaPagamento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejs.algaworksCurso.api.model.dto.out.formaPagamento.FormaPagamentoOut;
import com.ejs.algaworksCurso.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDisAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamentoOut formaPagamentoToFormaPagamentoOut( FormaPagamento formaPagamento) {
		return this.mapper.map(formaPagamento, FormaPagamentoOut.class);
	}
}
