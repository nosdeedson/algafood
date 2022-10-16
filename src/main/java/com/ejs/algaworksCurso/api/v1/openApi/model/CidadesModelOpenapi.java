package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.v1.model.out.cidade.CidadeOut;

public class CidadesModelOpenapi {

	private CidadeEmbeddedOpenApi _embedded;
	private Links _links;
	
	
	/**
	 * @return the _embedded
	 */
	public CidadeEmbeddedOpenApi get_embedded() {
		return _embedded;
	}


	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(CidadeEmbeddedOpenApi _embedded) {
		this._embedded = _embedded;
	}


	/**
	 * @return the _links
	 */
	public Links get_links() {
		return _links;
	}


	/**
	 * @param _links the _links to set
	 */
	public void set_links(Links _links) {
		this._links = _links;
	}

	public class CidadeEmbeddedOpenApi{
		private List<CidadeOut> cidades;

		/**
		 * @return the cidades
		 */
		public List<CidadeOut> getCidades() {
			return cidades;
		}

		/**
		 * @param cidades the cidades to set
		 */
		public void setCidades(List<CidadeOut> cidades) {
			this.cidades = cidades;
		}
		
	}
}
