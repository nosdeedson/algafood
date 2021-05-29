package com.ejs.algaworksCurso.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Endereco {
	
	@Column(name = "endereco_bairro")
	private String bairro;
	
	@Column(name = "endereco_cep")
	private String cep;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;
	
	@Column(name = "endereco_complemento")
	private String complemento;
	
	@Column(name = "endereco_numero")
	private String numero;
	
	@Column(name = "endereco_rua")
	private String rua;
	
	public Endereco() {}
	
	public Endereco(String cep, Cidade cidade, String complemento, String bairro, String rua) {
		this.cep = cep;
		this.cidade = cidade;
		this.complemento = complemento;
		this.bairro = bairro;
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	
}
