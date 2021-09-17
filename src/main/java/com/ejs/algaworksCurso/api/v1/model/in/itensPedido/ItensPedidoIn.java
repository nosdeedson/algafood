package com.ejs.algaworksCurso.api.v1.model.in.itensPedido;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class ItensPedidoIn {

	@NotNull
	private Long produtoId;
	
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	
	private String Observacao;

	/**
	 * @return the produtoId
	 */
	public Long getProdutoId() {
		return produtoId;
	}

	/**
	 * @param produtoId the produtoId to set
	 */
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return Observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		Observacao = observacao;
	}
	
	
}
