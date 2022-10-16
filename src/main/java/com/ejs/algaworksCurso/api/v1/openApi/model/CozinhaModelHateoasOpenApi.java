package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.v1.model.out.cozinha.CozinhaOut;

public class CozinhaModelHateoasOpenApi {

	private CozinhaEmbeddedOpenApi _embedded;
	private Links _links;
	private PageModel page;
	
	
		
	/**
	 * @return the page
	 */
	public PageModel getPage() {
		return page;
	}



	/**
	 * @param page the page to set
	 */
	public void setPage(PageModel page) {
		this.page = page;
	}



	/**
	 * @return the _embedded
	 */
	public CozinhaEmbeddedOpenApi get_embedded() {
		return _embedded;
	}



	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(CozinhaEmbeddedOpenApi _embedded) {
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

	public class CozinhaEmbeddedOpenApi{
		private List<CozinhaOut> cozinhas;

		/**
		 * @return the cozinhas
		 */
		public List<CozinhaOut> getCozinhas() {
			return cozinhas;
		}

		/**
		 * @param cozinhas the cozinhas to set
		 */
		public void setCozinhas(List<CozinhaOut> cozinhas) {
			this.cozinhas = cozinhas;
		}
		
	}
}
