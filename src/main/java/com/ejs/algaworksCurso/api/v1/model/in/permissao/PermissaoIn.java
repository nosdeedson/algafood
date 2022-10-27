package com.ejs.algaworksCurso.api.v1.model.in.permissao;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class PermissaoIn {

	@Schema(example = "Pode editar pedido")
	@NotBlank
	private String descricao;

	@Schema(example = "Editar Pedido")
	@NotBlank
	private String nome;
	
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
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
