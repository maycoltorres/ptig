package com.gamasoft.hps.sab.dto;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ImpuestoDto {

	private Long id;
	private Long idPais;
	private String nombre;
	private Set<RegimenDto> regimenes;
	
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

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	public Set<RegimenDto> getRegimenes() {
		return regimenes;
	}

	public void setRegimenes(Set<RegimenDto> regimenes) {
		this.regimenes = regimenes;
	}
		
}
