package com.ejs.algaworksCurso.api.v1.model.in.cidade;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ejs.algaworksCurso.api.v1.model.in.estado.EstadoResumidoDTO;

public class CidadeIn {

	@NotEmpty
	private String nome;
	
	@Valid
	@NotNull
	private EstadoResumidoDTO estado;

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
	public EstadoResumidoDTO getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoResumidoDTO estado) {
		this.estado = estado;
	}

	
}
