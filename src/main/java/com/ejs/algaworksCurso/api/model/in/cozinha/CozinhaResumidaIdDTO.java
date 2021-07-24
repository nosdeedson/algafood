package com.ejs.algaworksCurso.api.model.in.cozinha;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class CozinhaResumidaIdDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@NotNull
	private Long id;
	
	public CozinhaResumidaIdDTO() {}
	
	public CozinhaResumidaIdDTO(Long id) {
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
