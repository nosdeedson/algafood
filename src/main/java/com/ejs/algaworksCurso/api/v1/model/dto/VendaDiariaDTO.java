package com.ejs.algaworksCurso.api.v1.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;


public class VendaDiariaDTO extends RepresentationModel<VendaDiariaDTO>{
	
	private OffsetDateTime data;
	private Long quantidadeVendas;
	private BigDecimal totalFaturado;
	
	public VendaDiariaDTO() {}
	
	public VendaDiariaDTO(OffsetDateTime data, Long quantidadeVendas, BigDecimal totalFaturado) {
		this.data = data;
		this.quantidadeVendas = quantidadeVendas;
		this.totalFaturado = totalFaturado;
	}
	/**
	 * @return the data
	 */
	public OffsetDateTime getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(OffsetDateTime data) {
		this.data = data;
	}
	/**
	 * @return the quantidadeVendas
	 */
	public Long getQuantidadeVendas() {
		return quantidadeVendas;
	}
	/**
	 * @param totalVendas the quantidadeVendas to set
	 */
	public void setQuantidadeVendas(Long quantidadeVendas) {
		this.quantidadeVendas = quantidadeVendas;
	}
	/**
	 * @return the totalFaturado
	 */
	public BigDecimal getTotalFaturado() {
		return totalFaturado;
	}
	/**
	 * @param totalFaturado the totalFaturado to set
	 */
	public void setTotalFaturado(BigDecimal totalFaturado) {
		this.totalFaturado = totalFaturado;
	}
		
}
