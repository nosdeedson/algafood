package com.ejs.algaworksCurso.api.v1.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.v1.model.out.usuario.UsuarioOut;

public class UsuariosModelOpenApi {
	
	private UsuarioEmbeddedOpenApi _embedded;
	private Links _links;
		
	/**
	 * @return the _embedded
	 */
	public UsuarioEmbeddedOpenApi get_embedded() {
		return _embedded;
	}


	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(UsuarioEmbeddedOpenApi _embedded) {
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


	public class UsuarioEmbeddedOpenApi{
		private List<UsuarioOut> usuarios;

		/**
		 * @return the usuarios
		 */
		public List<UsuarioOut> getUsuarios() {
			return usuarios;
		}

		/**
		 * @param usuarios the usuarios to set
		 */
		public void setUsuarios(List<UsuarioOut> usuarios) {
			this.usuarios = usuarios;
		}
		
	}

}
