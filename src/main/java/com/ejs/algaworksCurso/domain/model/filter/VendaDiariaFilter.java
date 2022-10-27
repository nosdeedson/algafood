package com.ejs.algaworksCurso.domain.model.filter;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class VendaDiariaFilter {

	@Schema(example = "1", description = "id do restaurante")
	private Long restauranteId;

	@Schema(example = "2022-10-25T22:06:30.78945612Z", description = "data início da pesquisa")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;

	@Schema(example = "2022-10-25T22:06:30.78945612Z", description = "data final da pesquisa")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;
	/**
	 * @return the restauranteId
	 */
	public Long getRestauranteId() {
		return restauranteId;
	}
	/**
	 * @param restauranteId the restauranteId to set
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
