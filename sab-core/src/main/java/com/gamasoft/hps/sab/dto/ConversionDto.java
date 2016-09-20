/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.dto;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author wospino
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversionDto {

	private Long id;
	private Long idUnidadOrigen;
	private Long idUnidadDestino;
	private Long idArticulo;
	private Double factor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdUnidadOrigen() {
		return idUnidadOrigen;
	}
	public void setIdUnidadOrigen(Long idUnidadOrigen) {
		this.idUnidadOrigen = idUnidadOrigen;
	}
	public Long getIdUnidadDestino() {
		return idUnidadDestino;
	}
	public void setIdUnidadDestino(Long idUnidadDestino) {
		this.idUnidadDestino = idUnidadDestino;
	}
	public Long getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}
	public Double getFactor() {
		return factor;
	}
	public void setFactor(Double factor) {
		this.factor = factor;
	}

}