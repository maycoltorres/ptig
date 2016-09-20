package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MunicipioDto {

	private String nombre;
	private Long id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private Long departamento_id;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getDepartamento_id() {
		return departamento_id;
	}
	public void setDepartamento_id(Long departamento_id) {
		this.departamento_id = departamento_id;
	}
	
	
}
