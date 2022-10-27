package com.ejs.algaworksCurso.api.exceptionHandler;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CampoComErro")
public class CampoComErro {

	@Schema(example = "nome atributo")
	private String nome;

	@Schema(example = "motivo erro")
	private String mensagem;
	
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
	public String getMensagem() {
		return mensagem;
	}
	/**
	 * @param menssagem the menssagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public static class Builder{
		private String nome;
		private String mensagem;
		
		public Builder() {}
		
		public Builder nome(String nome) {
			this.nome =  nome;
			return this;
		}
		public Builder mensagem(String mensagem) {
			this.mensagem = mensagem;
			return this;
		}
		
		public CampoComErro build() {
			CampoComErro campo = new CampoComErro();
			campo.nome = this.nome;
			campo.mensagem = this.mensagem;
			return campo;
		}
	}
	
}
