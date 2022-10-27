package com.ejs.algaworksCurso.api.v1.model.in.endereco;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ejs.algaworksCurso.api.v1.model.in.cidade.CidadeResumidaIdDTO;
import io.swagger.v3.oas.annotations.media.Schema;

public class EnderecoIn {

	@Schema(example = "Varginha")
	@NotBlank
	private String bairro;

	@Schema(example = "37514-000")
	@NotBlank
	private String cep;

	@Schema(example = "Itajubá")
	@Valid
	@NotNull
	private CidadeResumidaIdDTO cidade;

	@Schema(example = "Fundos")
	private String complemento;

	@Schema(example = "108")
	@NotBlank
	private String numero;

	@Schema(example = "Rua Zequinha Luiz")
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
