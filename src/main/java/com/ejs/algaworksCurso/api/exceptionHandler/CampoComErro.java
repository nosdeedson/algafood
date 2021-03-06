package com.ejs.algaworksCurso.api.exceptionHandler;

import io.swagger.annotations.ApiModelProperty;

public class CampoComErro {
	
	@ApiModelProperty(value = "Nome do atributo que tem valores incorretos")
	private String nome;
	
	@ApiModelProperty(value = "Informa o erro", example = "não deve estar vazio")
	private String menssagem;
	
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
	 * @return the menssagem
	 */
	public String getMenssagem() {
		return menssagem;
	}
	/**
	 * @param menssagem the menssagem to set
	 */
	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}
	
	public static class Builder{
		private String nome;
		private String menssagem;
		
		public Builder() {}
		
		public Builder nome(String nome) {
			this.nome =  nome;
			return this;
		}
		public Builder menssagem(String menssagem) {
			this.menssagem = menssagem;
			return this;
		}
		
		public CampoComErro build() {
			CampoComErro campo = new CampoComErro();
			campo.nome = this.nome;
			campo.menssagem = this.menssagem;
			return campo;
		}
	}
	
}
