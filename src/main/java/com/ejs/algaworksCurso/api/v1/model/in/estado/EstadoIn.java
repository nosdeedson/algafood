package com.ejs.algaworksCurso.api.v1.model.in.estado;

import javax.validation.constraints.NotNull;

public class EstadoIn {
	
	@NotNull
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
