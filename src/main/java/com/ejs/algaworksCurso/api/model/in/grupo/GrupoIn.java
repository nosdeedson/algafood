package com.ejs.algaworksCurso.api.model.in.grupo;

import javax.validation.constraints.NotBlank;

public class GrupoIn {
	
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
