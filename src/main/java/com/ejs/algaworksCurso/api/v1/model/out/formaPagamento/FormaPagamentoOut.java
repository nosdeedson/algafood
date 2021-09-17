package com.ejs.algaworksCurso.api.v1.model.out.formaPagamento;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;

@ApiModel("formasPagamento")
@Relation(collectionRelation = "formasPagamento")
public class FormaPagamentoOut extends RepresentationModel<FormaPagamentoOut> {
	
	private Long id;
	private String descricao;
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
