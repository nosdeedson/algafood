package com.ejs.algaworksCurso.api.v1.model.dto;

public class CredencialDTO {
	
	private String email;
	private String senha;
	
	public CredencialDTO() {};
	
	public CredencialDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
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
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
