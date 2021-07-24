package com.ejs.algaworksCurso.api.model.in.cozinha;

import javax.validation.constraints.NotEmpty;

public class CozinhaIn {

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
