package com.ejs.algaworksCurso.api.v1.model.in.restaurante;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.ejs.algaworksCurso.api.v1.model.in.cozinha.CozinhaResumidaIdDTO;
import com.ejs.algaworksCurso.api.v1.model.in.endereco.EnderecoIn;

public class RestauranteIn implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotNull
	private boolean ativo;
	
	@NotNull
	private boolean aberto;
	
	@NotEmpty
	private String nome;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaResumidaIdDTO cozinha;
	
	@Valid
	@NotNull
	private EnderecoIn endereco;
	
	public RestauranteIn() {
	}

	/**
	 * @return the ativo
	 */
	public boolean isAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return the aberto
	 */
	public boolean isAberto() {
		return aberto;
	}

	/**
	 * @param aberto the aberto to set
	 */
	public void setAberto(boolean aberto) {
		this.aberto = aberto;
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
	 * @return the taxaFrete
	 */
	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	/**
	 * @param taxaFrete the taxaFrete to set
	 */
	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}

	/**
	 * @return the cozinha
	 */
	public CozinhaResumidaIdDTO getCozinha() {
		return cozinha;
	}

	/**
	 * @param cozinha the cozinha to set
	 */
	public void setCozinha(CozinhaResumidaIdDTO cozinha) {
		this.cozinha = cozinha;
	}

	/**
	 * @return the endereco
	 */
	public EnderecoIn getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(EnderecoIn endereco) {
		this.endereco = endereco;
	}
	

}
