package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.v1.model.out.restautante.RestauranteOut;

public class RestaurantesModelOpenApi {

	private RestauranteEmbeddeOpenApi _embedded;
	private Links _links;
	
	
	/**
	 * @return the _embedded
	 */
	public RestauranteEmbeddeOpenApi get_embedded() {
		return _embedded;
	}


	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(RestauranteEmbeddeOpenApi _embedded) {
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


	public class RestauranteEmbeddeOpenApi{
		private List<RestauranteOut> restaurantes;

		/**
		 * @return the restaurantes
		 */
		public List<RestauranteOut> getRestaurantes() {
			return restaurantes;
		}

		/**
		 * @param restaurantes the restaurantes to set
		 */
		public void setRestaurantes(List<RestauranteOut> restaurantes) {
			this.restaurantes = restaurantes;
		}
		
	}
}
