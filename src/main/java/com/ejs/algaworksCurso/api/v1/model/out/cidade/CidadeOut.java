package com.ejs.algaworksCurso.api.v1.model.out.cidade;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ejs.algaworksCurso.api.v1.model.out.estado.EstadoResumidoOut;

import io.swagger.annotations.ApiModelProperty;

@Relation(collectionRelation = "cidades")
public class CidadeOut extends RepresentationModel<CidadeOut> {

	private Long id;
	private String nome;
	
	@ApiModelProperty(value = "estado")
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
