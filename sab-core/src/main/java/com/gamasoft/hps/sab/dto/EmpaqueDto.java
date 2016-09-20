package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author usuario
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class EmpaqueDto {

	private Long id;
	private Long idArticulo;
	private String nombreArticulo;
	private Long cantidad;
	private Long idUnidad;
	private String nombreUnidad;
	private String tipoUnidad;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public Long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	public String getNombreArticulo() {
		return nombreArticulo;
	}
	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}
	public String getNombreUnidad() {
		return nombreUnidad;
	}
	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}
	public String getTipoUnidad() {
		return tipoUnidad;
	}
	public void setTipoUnidad(String tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}
	
}
