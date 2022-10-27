package com.ejs.algaworksCurso.api.v1.model.out.restautante;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ejs.algaworksCurso.api.v1.model.out.cozinha.CozinhaOut;
import com.ejs.algaworksCurso.api.v1.model.out.endereco.EnderecoOut;

@Relation(collectionRelation = "restaurantes")
public class RestauranteOut extends RepresentationModel<RestauranteOut> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Fogão a Lenha")
	private String nome;

	@Schema(example = "true")
	private boolean aberto;

	@Schema(example = "true")
	private boolean ativo;


	private CozinhaOut cozinha;

	@Schema(example = "10")
	private BigDecimal taxaFrete;

	private EnderecoOut endereco;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isAberto() {
		return aberto;
	}
	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public CozinhaOut getCozinha() {
		return cozinha;
	}
	public void setCozinha(CozinhaOut cozinha) {
		this.cozinha = cozinha;
	}
	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}
	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}
	public EnderecoOut getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoOut endereco) {
		this.endereco = endereco;
	}
	
	
}
