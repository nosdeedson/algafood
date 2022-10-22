package com.ejs.algaworksCurso.api.v1.model.out.estado;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EstadoResumidoOut extends RepresentationModel<EstadoResumidoOut> {
	
	@Schema(example = "1")
	private Long id;

	@Schema(example = "Minas Gerais")
	@JsonIgnore
	private String nome;
	

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
	

}
