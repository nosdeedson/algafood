package com.ejs.algaworksCurso.api.v1.model.in.senha;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class SenhaAtualizarIn {

	@Schema(example = "1234", description = "senha sendo usada atualmente")
	@NotBlank(message = "Senha atual deve ser informada.")
	private String senhaAtual;

	@Schema(example = "123456", description = "senha a ser usada após a edição")
	@NotBlank(message = "Nova senha deve ser informada.")
	private String novaSenha;
	/**
	 * @return the senhaAtual
	 */
	public String getSenhaAtual() {
		return senhaAtual;
	}
	/**
	 * @param senhaAtual the senhaAtual to set
	 */
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	/**
	 * @return the novaSenha
	 */
	public String getNovaSenha() {
		return novaSenha;
	}
	/**
	 * @param novaSenha the novaSenha to set
	 */
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	

}
