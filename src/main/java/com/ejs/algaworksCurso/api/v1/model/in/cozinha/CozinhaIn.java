package com.ejs.algaworksCurso.api.v1.model.in.cozinha;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class CozinhaIn {

	@ApiModelProperty(required = true)
	@NotEmpty
	private String nome;

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
