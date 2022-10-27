package com.ejs.algaworksCurso.api.v1.model.in.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioAtualizarIn {

	@Schema(example = "Edson Jose de Souza")
	@NotBlank
	private String nome;

	@Schema(example = "edson@gmail.com")
	@Email
	private String email;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	

}
