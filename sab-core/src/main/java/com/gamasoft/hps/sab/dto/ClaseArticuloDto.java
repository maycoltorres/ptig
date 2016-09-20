package com.gamasoft.hps.sab.dto;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ClaseArticuloDto {

	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean venta;
	private Long idCliente;
	private Boolean activo;
	private Set<GrupoArticuloDto> grupos;
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getVenta() {
		return venta;
	}

	public void setVenta(Boolean venta) {
		this.venta = venta;
	}

	public Boolean getInventario() {
		return inventario;
	}

	public void setInventario(Boolean inventario) {
		this.inventario = inventario;
	}

	private Boolean inventario;
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Set<GrupoArticuloDto> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<GrupoArticuloDto> grupos) {
		this.grupos = grupos;
	}
		
}
