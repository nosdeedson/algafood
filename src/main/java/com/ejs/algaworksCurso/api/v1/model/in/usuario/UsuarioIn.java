package com.ejs.algaworksCurso.api.v1.model.in.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.ejs.algaworksCurso.domain.model.Genero;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioIn {

	@Schema(example = "edson@gmail.com")
	@Email
	@NotBlank
	private String email;

	@Schema(example = "Edson Jose de Souza")
	@NotBlank
	private String nome;

	@Schema(example = "123456")
	@NotBlank
	private String senha;

	private Genero genero;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}	
}
