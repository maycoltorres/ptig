package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartamentoDto {
	
	private Long id;
	private String nombre;
	private Long pais_id;
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

	public Long getPais_id() {
		return pais_id;
	}

	public void setPais_id(Long pais_id) {
		this.pais_id = pais_id;
	}
	
	
}
