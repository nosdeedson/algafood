package com.ejs.algaworksCurso.api.model.dto.out.cidade;

import com.ejs.algaworksCurso.api.model.dto.out.estado.EstadoResumidoOut;

public class CidadeOut {

	private Long id;
	private String nome;
	private EstadoResumidoOut estado;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	/**
	 * @return the estado
	 */
	public EstadoResumidoOut getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoResumidoOut estado) {
		this.estado = estado;
	}
	
	
}
