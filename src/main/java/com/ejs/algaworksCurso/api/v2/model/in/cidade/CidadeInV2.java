package com.ejs.algaworksCurso.api.v2.model.in.cidade;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CidadeInV2 {

	@NotEmpty
	private String nome;
	
	@Valid
	@NotNull
	private Long estadoId;

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

	/**
	 * @return the estado
	 */
	public Long getEstadoId() {
		return estadoId;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	
}
