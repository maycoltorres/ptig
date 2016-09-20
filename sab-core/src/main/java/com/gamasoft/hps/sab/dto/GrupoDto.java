package com.gamasoft.hps.sab.dto;

import java.util.List;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GrupoDto {

	private Long id;
	private String nombre;
	private List<ClienteDto> clientes;	
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

	public List<ClienteDto> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteDto> clientes) {
		this.clientes = clientes;
	}
	
}
