package com.ejs.algaworksCurso.api.v1.model.in.grupo;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class GrupoIn {
	
	@ApiModelProperty(example = "Gerencia", value = "Nome de grupo de acesso")
	@NotBlank
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
