package com.ejs.algaworksCurso.api.v1.model.in.formaPagamento;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

public class FormaPagamentoIn {

	@Schema(example = "Boleto")
	@NotEmpty
	private String descricao;

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
