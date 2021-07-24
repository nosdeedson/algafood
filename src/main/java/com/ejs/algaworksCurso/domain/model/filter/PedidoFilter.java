package com.ejs.algaworksCurso.domain.model.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class PedidoFilter {
	
	private Long clienteId;
	private Long restauranteId;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;

	/**
	 * @return the clienteId
	 */
	public Long getClienteId() {
		return clienteId;
	}

	/**
	 * @param clienteId the clienteId to set
	 */
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	/**
	 * @return the restuaranteId
	 */
	public Long getRestauranteId() {
		return restauranteId;
	}

	/**
	 * @param restuaranteId the restuaranteId to set
	 */
	public void setRestauranteId(Long restauranteId) {
		this.restauranteId = restauranteId;
	}

	/**
	 * @return the dataCriacaoInicio
	 */
	public OffsetDateTime getDataCriacaoInicio() {
		return dataCriacaoInicio;
	}

	/**
	 * @param dataCriacaoInicio the dataCriacaoInicio to set
	 */
	public void setDataCriacaoInicio(OffsetDateTime dataCriacaoInicio) {
		this.dataCriacaoInicio = dataCriacaoInicio;
	}

	/**
	 * @return the dataCriacaoFim
	 */
	public OffsetDateTime getDataCriacaoFim() {
		return dataCriacaoFim;
	}

	/**
	 * @param dataCriacaoFim the dataCriacaoFim to set
	 */
	public void setDataCriacaoFim(OffsetDateTime dataCriacaoFim) {
		this.dataCriacaoFim = dataCriacaoFim;
	}

	
}
