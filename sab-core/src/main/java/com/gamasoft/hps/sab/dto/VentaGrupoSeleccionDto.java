package com.gamasoft.hps.sab.dto;
/**
 * 
 * @author wospino
 */
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gamasoft.hps.sab.domain.Inventario;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VentaGrupoSeleccionDto {

	private Long idVenta;
	private Long idGrupoSeleccion;
	private Double cantidad;
	public Long getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}
	public Long getIdGrupoSeleccion() {
		return idGrupoSeleccion;
	}
	public void setIdGrupoSeleccion(Long idGrupoSeleccion) {
		this.idGrupoSeleccion = idGrupoSeleccion;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
}
