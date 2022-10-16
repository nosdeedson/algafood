package com.ejs.algaworksCurso.api.v1.model.in.estado;

import javax.validation.constraints.NotNull;

public class EstadoResumidoDTO {
	
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
