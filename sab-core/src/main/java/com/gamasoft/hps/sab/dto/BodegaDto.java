package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author usuario
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class BodegaDto {

	private Long id;
	private String nombre;
	private Long idPunto;
	private Boolean bodegaInterna;
	private Boolean activo;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPunto() {
		return idPunto;
	}

	public void setIdPunto(Long idPunto) {
		this.idPunto = idPunto;
	}

	public Boolean getBodegaInterna() {
		return bodegaInterna;
	}

	public void setBodegaInterna(Boolean bodegaInterna) {
		this.bodegaInterna = bodegaInterna;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
}
