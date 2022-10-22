package com.ejs.algaworksCurso.api.v1.model.out.cidade;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ejs.algaworksCurso.api.v1.model.out.estado.EstadoResumidoOut;

@Relation(collectionRelation = "cidades")
public class CidadeOut extends RepresentationModel<CidadeOut> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "itajubá")
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
