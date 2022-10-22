package com.ejs.algaworksCurso.api.v1.model.in.estado;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class EstadoResumidoDTO {

	@Schema(name = "id", example = "1")
	@NotNull
	private Long id;

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
	

}
