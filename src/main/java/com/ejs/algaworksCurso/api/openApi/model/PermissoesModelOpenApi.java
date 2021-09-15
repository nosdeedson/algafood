package com.ejs.algaworksCurso.api.openApi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.ejs.algaworksCurso.api.model.out.permissao.PermissaoOut;

import io.swagger.annotations.ApiModel;

@ApiModel("permissões")
public class PermissoesModelOpenApi {
	
	private PermissaoEmbeddedOpenApi _embedded;
	private Links _links;
	

	/**
	 * @return the _embedded
	 */
	public PermissaoEmbeddedOpenApi get_embedded() {
		return _embedded;
	}


	/**
	 * @param _embedded the _embedded to set
	 */
	public void set_embedded(PermissaoEmbeddedOpenApi _embedded) {
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


	public class PermissaoEmbeddedOpenApi{
		private List<PermissaoOut>  permissoes;

		/**
		 * @return the permissoes
		 */
		public List<PermissaoOut> getPermissoes() {
			return permissoes;
		}

		/**
		 * @param permissoes the permissoes to set
		 */
		public void setPermissoes(List<PermissaoOut> permissoes) {
			this.permissoes = permissoes;
		}
		
	}
}
