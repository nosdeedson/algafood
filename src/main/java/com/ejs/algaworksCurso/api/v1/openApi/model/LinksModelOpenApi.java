package com.ejs.algaworksCurso.api.v1.openApi.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "links")
public class LinksModelOpenApi {
	
	private LinkModel rel;
	
	/**
	 * @return the rel
	 */
	public LinkModel getRel() {
		return rel;
	}

	/**
	 * @param rel the rel to set
	 */
	public void setRel(LinkModel rel) {
		this.rel = rel;
	}

	@ApiModel(value = "link")
	public class LinkModel{
		private String href;
		private boolean template;
		/**
		 * @return the href
		 */
		public String getHref() {
			return href;
		}
		/**
		 * @param href the href to set
		 */
		public void setHref(String href) {
			this.href = href;
		}
		/**
		 * @return the template
		 */
		public boolean isTemplate() {
			return template;
		}
		/**
		 * @param template the template to set
		 */
		public void setTemplate(boolean template) {
			this.template = template;
		}
	
		
				
	}
}
