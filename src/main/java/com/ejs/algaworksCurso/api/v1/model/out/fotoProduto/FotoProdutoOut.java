package com.ejs.algaworksCurso.api.v1.model.out.fotoProduto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;

@Relation(collectionRelation = "fotoProduto")
public class FotoProdutoOut extends RepresentationModel<FotoProdutoOut>{
	
	@ApiModelProperty(value = "Nome do arquivo")
	private String nomeArquivo;
	
	@ApiModelProperty(value = "Descrição da imagem")
	private String descricao;
	
	@ApiModelProperty(value = "Tipo de arquivo", example = "png/jpeg")
	private String contentType;
	
	@ApiModelProperty(value = "Tamanho do arquivo")
	private Long tamanho;
	
	/**
	 * @return the nomeArquivo
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	/**
	 * @param nomeArquivo the nomeArquivo to set
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the tamanho
	 */
	public Long getTamanho() {
		return tamanho;
	}
	/**
	 * @param tamanho the tamanho to set
	 */
	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}
	
	
}
