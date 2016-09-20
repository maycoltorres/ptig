package com.gamasoft.hps.sab.dto;
/**
 * 
 * @author wospino
 */
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gamasoft.hps.sab.domain.Inventario;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VentaDto {

	private Long idArticulo;
	
	private Long idGrupoArticulo;
	
	private Long idClaseArticulo;
	
	private List<VentaCanalImpuestoDto> canalesImpuestos;
	
	private List<VentaGrupoSeleccionDto> gruposSeleccion;
	
	private List<UnidadVentaDto> unidadesVenta;
	
	public List<VentaCanalImpuestoDto> getCanalesImpuestos() {
		return canalesImpuestos;
	}

	public void setCanalesImpuestos(List<VentaCanalImpuestoDto> canalesImpuestos) {
		this.canalesImpuestos = canalesImpuestos;
	}

	public Long getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Long getIdGrupoArticulo() {
		return idGrupoArticulo;
	}

	public void setIdGrupoArticulo(Long idGrupoArticulo) {
		this.idGrupoArticulo = idGrupoArticulo;
	}

	public List<VentaGrupoSeleccionDto> getGruposSeleccion() {
		return gruposSeleccion;
	}

	public void setGruposSeleccion(List<VentaGrupoSeleccionDto> gruposSeleccion) {
		this.gruposSeleccion = gruposSeleccion;
	}

	public List<UnidadVentaDto> getUnidadesVenta() {
		return unidadesVenta;
	}

	public void setUnidadesVenta(List<UnidadVentaDto> unidadesVenta) {
		this.unidadesVenta = unidadesVenta;
	}

	public Long getIdClaseArticulo() {
		return idClaseArticulo;
	}

	public void setIdClaseArticulo(Long idClaseArticulo) {
		this.idClaseArticulo = idClaseArticulo;
	}
	
}
