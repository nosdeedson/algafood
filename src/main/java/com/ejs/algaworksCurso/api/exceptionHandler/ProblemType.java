package com.ejs.algaworksCurso.api.exceptionHandler;

public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	ERRO_NEGOCIO("violação de regra de negócio", "/erro-negocio");
	
	private String title;
	private String uri;
	
	private ProblemType(String title, String path) {
		this.title = title;
		this.uri = "https://algafood.com.br" + path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
	
}
