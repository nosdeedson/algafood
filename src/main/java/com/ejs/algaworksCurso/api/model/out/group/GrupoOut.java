package com.ejs.algaworksCurso.api.model.out.group;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.Api;

@Api(tags = "grupos")
public class GrupoOut extends RepresentationModel<GrupoOut>{
	
	private Long id;
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
