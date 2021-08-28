package com.ejs.algaworksCurso.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "URI")
public class StringUriResposta {
	
	@ApiModelProperty(value = "URL do recurso atualizado.", example = "http://localhost:8080/recursoSolicitado/1")
	private String uri;
	
	public StringUriResposta() {}
	
	public StringUriResposta(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	

}
