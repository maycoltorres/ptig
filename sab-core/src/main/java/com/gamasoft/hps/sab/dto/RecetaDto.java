package com.gamasoft.hps.sab.dto;
import java.util.List;

/**
 * 
 * @author wospino
 */
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecetaDto {
	
	private Long id;
	private Long idArticulo;
	private Long idUnidad;
	private String nombre;
	private String preparacion;
	private Integer cantidad;
	private Boolean activo;
	private Boolean predeterminada;
	
	private List<CanalDto> canales;
	private List<PuntoDto> puntos;
	private List<IngredienteDto> ingredientes;
	
	
	public Long getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPreparacion() {
		return preparacion;
	}
	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}
	public Long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public Boolean getPredeterminada() {
		return predeterminada;
	}
	public void setPredeterminada(Boolean predeterminada) {
		this.predeterminada = predeterminada;
	}
	public List<CanalDto> getCanales() {
		return canales;
	}
	public void setCanales(List<CanalDto> canales) {
		this.canales = canales;
	}
	public List<PuntoDto> getPuntos() {
		return puntos;
	}
	public void setPuntos(List<PuntoDto> puntos) {
		this.puntos = puntos;
	}
	public List<IngredienteDto> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<IngredienteDto> ingredientes) {
		this.ingredientes = ingredientes;
	}
}
