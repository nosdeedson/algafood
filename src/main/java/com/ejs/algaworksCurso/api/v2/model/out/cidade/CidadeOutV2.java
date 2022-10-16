package com.ejs.algaworksCurso.api.v2.model.out.cidade;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
public class CidadeOutV2 extends RepresentationModel<CidadeOutV2>  {

	private Long idCidade;
	private String nomeCidade;
	
	private Long estadoId;

	/**
	 * @return the idCidade
	 */
	public Long getIdCidade() {
		return idCidade;
	}

	/**
	 * @param idCidade the idCidade to set
	 */
	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	/**
	 * @return the nomeCidade
	 */
	public String getNomeCidade() {
		return nomeCidade;
	}

	/**
	 * @param nomeCidade the nomeCidade to set
	 */
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	/**
	 * @return the estadoId
	 */
	public Long getEstadoId() {
		return estadoId;
	}

	/**
	 * @param estadoId the estadoId to set
	 */
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
	
	
	
}
