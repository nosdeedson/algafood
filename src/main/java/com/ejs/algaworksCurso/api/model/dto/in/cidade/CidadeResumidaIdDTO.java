package com.ejs.algaworksCurso.api.model.dto.in.cidade;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CidadeResumidaIdDTO {
	
	@Valid
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
