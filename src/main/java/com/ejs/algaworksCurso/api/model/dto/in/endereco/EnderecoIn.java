package com.ejs.algaworksCurso.api.model.dto.in.endereco;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ejs.algaworksCurso.api.model.dto.in.cidade.CidadeResumidaIdDTO;

public class EnderecoIn {
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String cep;
	
	@Valid
	@NotNull
	private CidadeResumidaIdDTO cidade;
	
	private String complemento;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String logradouro;
	
	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}
	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}
	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	/**
	 * @return the cidade
	 */
	public CidadeResumidaIdDTO getCidade() {
		return cidade;
	}
	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(CidadeResumidaIdDTO cidade) {
		this.cidade = cidade;
	}
	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}
	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}
	/**
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	

}
