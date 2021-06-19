package com.ejs.algaworksCurso.api.exceptionHandler;

public enum ProblemType {
	
	DADOS_INVALIDOS("Dados inválidos", "/dados-invalidos"),
	ERRO_DE_SISTEMA("Erro interno no sistema", "/erro-sistema"),
	PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
	REQUISICAO_MAL_FORMADA("Requisição mal formada", "/requisicao-mal-formada"),
	RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
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
