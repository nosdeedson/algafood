package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Pageable")
public class PageableModelOpenApi {

	@ApiModelProperty(value = "A página a ser retornada", example = "0")
	private int page;
	
	@ApiModelProperty(value = "Quantos elementos a págima terá", example = "10")
	private int size;
	
	@ApiModelProperty(value = "Valores para fazer a ordenação", example = "nome,ASC")
	private List<String> sort;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<String> getSort() {
		return sort;
	}
	public void setSort(List<String> sort) {
		this.sort = sort;
	}
	
	
}
