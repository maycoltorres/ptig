package com.gamasoft.hps.sab.dto;
/**
 * 
 * @author wospino
 */
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gamasoft.hps.sab.domain.Inventario;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InventarioDto {

	private Long idArticulo;
	private Long idUnidadKardex;
	private Long idUnidadAlterna;
	private Long idClaseArticulo;
	private Long idGrupoArticulo;
	private Boolean esSeleccion;
	private String aliasUnidad;
	
	private List<MaximosMinimosDto> maximosMinimos;
	
	private List<UnidadDto> unidadesCompra;
	
	private List<BodegaDto> bodegas;
	
	public Long getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}
	
	public Boolean getEsSeleccion() {
		return esSeleccion;
	}

	public void setEsSeleccion(Boolean esSeleccion) {
		this.esSeleccion = esSeleccion;
	}

	public Long getIdUnidadKardex() {
		return idUnidadKardex;
	}

	public void setIdUnidadKardex(Long idUnidadKardex) {
		this.idUnidadKardex = idUnidadKardex;
	}

	public Long getIdUnidadAlterna() {
		return idUnidadAlterna;
	}

	public void setIdUnidadAlterna(Long idUnidadAlterna) {
		this.idUnidadAlterna = idUnidadAlterna;
	}

	public Long getIdGrupoArticulo() {
		return idGrupoArticulo;
	}

	public void setIdGrupoArticulo(Long idGrupoArticulo) {
		this.idGrupoArticulo = idGrupoArticulo;
	}
	
	public Long getIdClaseArticulo() {
		return idClaseArticulo;
	}
	
	public String getAliasUnidad() {
		return aliasUnidad;
	}

	public void setAliasUnidad(String aliasUnidad) {
		this.aliasUnidad = aliasUnidad;
	}

	public void setIdClaseArticulo(Long idClaseArticulo) {
		this.idClaseArticulo = idClaseArticulo;
	}

	public List<MaximosMinimosDto> getMaximosMinimos() {
		return maximosMinimos;
	}
	
	public void setMaximosMinimos(List<MaximosMinimosDto> maximosMinimos) {
		this.maximosMinimos = maximosMinimos;
	}
	
	public List<UnidadDto> getUnidadesCompra() {
		return unidadesCompra;
	}

	public void setUnidadesCompra(List<UnidadDto> unidadesCompra) {
		this.unidadesCompra = unidadesCompra;
	}

	public List<BodegaDto> getBodegas() {
		return bodegas;
	}

	public void setBodegas(List<BodegaDto> bodegas) {
		this.bodegas = bodegas;
	}
	
}
