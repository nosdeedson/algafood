package com.ejs.algaworksCurso.api.model.dto.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class CozinhaResumidaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@NotNull
	private Long id;
	
	public CozinhaResumidaDTO() {}
	
	public CozinhaResumidaDTO(Long id) {
		this.id = id;
	}

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
