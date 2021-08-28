package com.ejs.algaworksCurso.api.openApi.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PagedModelOpenApi<T> {
	
	private List<T> content;
	
	@ApiModelProperty(value = "Quantidade de elementos da página", example = "10")
	private Long size;
	
	@ApiModelProperty(value = "Total de elementos no Banco de Dados", example = "50")
	private Long totalElements;
	
	@ApiModelProperty(value = "Total de páginas geradas", example = "10")
	private Long totalPages;
	
	@ApiModelProperty(value = "Página atual", example = "0")
	private Long number;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	
	
}
