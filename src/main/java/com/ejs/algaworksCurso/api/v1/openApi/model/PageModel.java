package com.ejs.algaworksCurso.api.v1.openApi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("page")
public class PageModel {

	@ApiModelProperty(value = "Quantidade de elementos da página", example = "10")
	private Long size;
	
	@ApiModelProperty(value = "Total de elementos no Banco de Dados", example = "50")
	private Long totalElements;
	
	@ApiModelProperty(value = "Total de páginas geradas", example = "10")
	private Long totalPages;
	
	@ApiModelProperty(value = "Página atual", example = "0")
	private Long number;

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * @return the totalElements
	 */
	public Long getTotalElements() {
		return totalElements;
	}

	/**
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	/**
	 * @return the totalPages
	 */
	public Long getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Long number) {
		this.number = number;
	}
	
	
}
