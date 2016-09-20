package com.gamasoft.hps.sab.dto;
/**
 * 
 * @author wospino
 */
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeleccionDto {

	private Long id;
	private Long idGrupoSeleccion;
	private Long idArticulo;
	private Long idUnidad;
	private Double cantidadADescargar;
	private Double incrementoPrecio;
	private String nombreArticulo;
	private String nombreUnidad;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdGrupoSeleccion() {
		return idGrupoSeleccion;
	}
	public void setIdGrupoSeleccion(Long idGrupoSeleccion) {
		this.idGrupoSeleccion = idGrupoSeleccion;
	}
	public Long getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}
	public Double getCantidadADescargar() {
		return cantidadADescargar;
	}
	public void setCantidadADescargar(Double cantidad) {
		this.cantidadADescargar = cantidad;
	}
	public Double getIncrementoPrecio() {
		return incrementoPrecio;
	}
	public void setIncrementoPrecio(Double incrementoPrecio) {
		this.incrementoPrecio = incrementoPrecio;
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
}
