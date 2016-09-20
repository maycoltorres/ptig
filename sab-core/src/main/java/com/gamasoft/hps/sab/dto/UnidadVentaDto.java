package com.gamasoft.hps.sab.dto;
/**
 * 
 * @author wospino
 */
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gamasoft.hps.sab.domain.Inventario;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnidadVentaDto {

	private Long idVenta;
	private Long idUnidad;
	private Long idListaPrecios;
	private Float valor;
	private Boolean principal;
	
	public Long getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}
	public Long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	public Long getIdListaPrecios() {
		return idListaPrecios;
	}
	public void setIdListaPrecios(Long idListaPrecios) {
		this.idListaPrecios = idListaPrecios;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Boolean getPrincipal() {
		return principal;
	}
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}
	
}
