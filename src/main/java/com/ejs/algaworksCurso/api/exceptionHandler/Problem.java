package com.ejs.algaworksCurso.api.exceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Problema")
@JsonInclude(Include.NON_NULL)
public class Problem {
	
	@ApiModelProperty(example = "400", value = "código HTTP da resposta")
	private Integer status;
	
	@ApiModelProperty(example = "https://algafood.com.br/dados-invalidos")
	private String type;
	
	@ApiModelProperty(example = "Dados inválidos")	
	private String title;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos, corrija, e tente novamente.")
	private String detail;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos, corrija, e tente novamente.")
	private String userMessage;
	
	@ApiModelProperty(example = "25/08/2021 01:27:54")
	private String timeStamp;
	
	private List<CampoComErro> camposComErro = new ArrayList<>();
	
	public static class Builder{
		private Integer status;
		private String type;
		private String title;
		private String detail;
		
		private String userMessage;
		private String timeStamp;
		
		private List<CampoComErro> camposComErro;
		
		public Builder() {
		}
		
		public Builder status(Integer status) {
			this.status = status;
			return this;
		}
		public Builder type( String type) {
			this.type = type;
			return this;
		}
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}
		public Builder userMessage(String userMessage) {
			this.userMessage = userMessage;
			return this;
		}
		public Builder timeStamp() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
			this.timeStamp = LocalDateTime.now().format(formatter);
			return this;
		}
		
		public Builder camposComErro(List<CampoComErro> camposComErro) {
			this.camposComErro = camposComErro;
			return this;
		}
		
		public Problem build() {
			Problem p = new Problem();
			p.status = this.status;
			p.type = this.type;
			p.title = this.title;
			p.detail = this.detail;
			p.timeStamp = this.timeStamp;
			p.userMessage = this.userMessage;
			p.camposComErro = this.camposComErro;
			return p;
		}
	}
	
	private Problem() {}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the userMessage
	 */
	public String getUserMessage() {
		return userMessage;
	}

	/**
	 * @param userMessage the userMessage to set
	 */
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the camposComErro
	 */
	public List<CampoComErro> getCamposComErro() {
		return camposComErro;
	}

	/**
	 * @param camposComErro the camposComErro to set
	 */
	public void setCamposComErro(List<CampoComErro> camposComErro) {
		this.camposComErro = camposComErro;
	}
	
	
	
}
