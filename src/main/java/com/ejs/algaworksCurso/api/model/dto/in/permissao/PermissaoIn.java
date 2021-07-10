package com.ejs.algaworksCurso.api.model.dto.in.permissao;

import javax.validation.constraints.NotBlank;

public class PermissaoIn {

	@NotBlank
	private String descricao;
	
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
