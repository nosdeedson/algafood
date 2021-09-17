package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.v1.model.out.group.GrupoOut;

import io.swagger.annotations.ApiModel;

@ApiModel("grupos")
public class GruposModelOpenApi {
	
	private GrupoEmbeddedOpenApi _embedded;
	private Links _links;
	
	/**
	 * @return the _embedded
	 */
	public GrupoEmbeddedOpenApi get_embedded() {
		return _embedded;
	}



	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(GrupoEmbeddedOpenApi _embedded) {
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



	public class GrupoEmbeddedOpenApi{
		private List<GrupoOut> grupos;

		/**
		 * @return the grupos
		 */
		public List<GrupoOut> getGrupos() {
			return grupos;
		}

		/**
		 * @param grupos the grupos to set
		 */
		public void setGrupos(List<GrupoOut> grupos) {
			this.grupos = grupos;
		}
		
	}
}
