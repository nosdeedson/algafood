package com.ejs.algaworksCurso.api.v1.model.in.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.ejs.algaworksCurso.domain.model.Genero;

public class UsuarioIn {

	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
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
