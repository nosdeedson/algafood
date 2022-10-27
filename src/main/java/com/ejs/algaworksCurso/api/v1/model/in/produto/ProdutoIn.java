package com.ejs.algaworksCurso.api.v1.model.in.produto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class ProdutoIn {

	@Schema(example = "true", description = "indica se o restaurante serve o produto")
	@NotNull
	private Boolean ativo;

	@Schema(example = "Arroz com queizo e tomate")
	@NotBlank
	private String descricao;

	@Schema(example = "Arroz ao Forno")
	@NotBlank
	private String nome;

	@Schema(example = "10")
	@PositiveOrZero
	@NotNull
	private BigDecimal preco;
	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}
	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
	 * @return the preco
	 */
	public BigDecimal getPreco() {
		return preco;
	}
	/**
	 * @param preco the preco to set
	 */
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	
}
