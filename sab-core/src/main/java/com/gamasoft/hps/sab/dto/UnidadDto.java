/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.dto;

import java.util.List;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author wospino
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnidadDto {
	private Long id;
	private Long idCliente;
	private String nombre;
	private Boolean estandar;
	private Double factor;
	private String tipo;
	private List<ConversionDto> conversiones;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Boolean getEstandar() {
		return estandar;
	}
	public void setEstandar(Boolean estandar) {
		this.estandar = estandar;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Double getFactor() {
		return factor;
	}
	public void setFactor(Double factor) {
		this.factor = factor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<ConversionDto> getConversiones() {
		return conversiones;
	}
	public void setConversion(List<ConversionDto> conversiones) {
		this.conversiones = conversiones;
	}	
}