package com.ejs.algaworksCurso.api.v1.model.out.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;

@Relation(collectionRelation = "endereco entrega")
public class EnderecoOut extends RepresentationModel<EnderecoOut> {

	@Schema(example = "Varginha")
	private String bairro;

	@Schema(example = "37514-000")
	private String cep;

	@Schema(example = "Delfim Moreira")
	private CidadeOut cidade;

	@Schema(example = "Fundos")
	private String complemento;

	@Schema(example = "108")
	private String numero;

	@Schema(example = "Rua Zequinha Luiz")
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
	public CidadeOut getCidade() {
		return cidade;
	}
	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(CidadeOut cidade) {
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
