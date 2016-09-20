package com.gamasoft.hps.sab.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MarcaDto {

	private Long id;
	private String nombre;
	private Long idCliente;
	private boolean Activo;
	private List<PuntoDto> puntos;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public boolean isActivo() {
		return Activo;
	}
	public void setActivo(boolean activo) {
		Activo = activo;
	}
	public List<PuntoDto> getPuntos() {
		return puntos;
	}
	public void setPuntos(List<PuntoDto> puntos) {
		this.puntos = puntos;
	}
	
	
	
	
	
}