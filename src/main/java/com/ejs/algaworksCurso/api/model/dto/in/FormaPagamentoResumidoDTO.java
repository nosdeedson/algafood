package com.ejs.algaworksCurso.api.model.dto.in;

import javax.validation.constraints.NotNull;

public class FormaPagamentoResumidoDTO {

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
