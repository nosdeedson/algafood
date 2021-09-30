package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.v1.model.out.estado.EstadoOut;

import io.swagger.annotations.ApiModel;

@ApiModel("EstadosModel")
public class EstadosModelOpenApi {
	
	private EstadoEmbeddedOpenApi _embedded;
	private Links _links;
	
	
	/**
	 * @return the _embedded
	 */
	public EstadoEmbeddedOpenApi get_embedded() {
		return _embedded;
	}



	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(EstadoEmbeddedOpenApi _embedded) {
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



	public class EstadoEmbeddedOpenApi{
		
		private List<EstadoOut> estados;

		/**
		 * @return the estados
		 */
		public List<EstadoOut> getEstados() {
			return estados;
		}

		/**
		 * @param estados the estados to set
		 */
		public void setEstados(List<EstadoOut> estados) {
			this.estados = estados;
		}
		
	}

}
