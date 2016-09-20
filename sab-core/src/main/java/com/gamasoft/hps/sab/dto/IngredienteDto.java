package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author usuario
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class IngredienteDto {

	private Long id;
	private Long idReceta;
	private Long idArticulo;
	private Long idUnidad;
	private Integer cantidad;
	private String nombreUnidad;
	private String nombreArticulo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdReceta() {
		return idReceta;
	}
	public void setIdReceta(Long idReceta) {
		this.idReceta = idReceta;
	}
	public Long getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombreUnidad() {
		return nombreUnidad;
	}
	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}
	public String getNombreArticulo() {
		return nombreArticulo;
	}
	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}
	public Long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	
}
