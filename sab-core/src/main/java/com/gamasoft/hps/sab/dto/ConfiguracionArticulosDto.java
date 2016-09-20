package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ConfiguracionArticulosDto {

	private Long idCliente;
	private Boolean usaCodigoBarras;
	private Boolean usaUnidadAlterna;
	private Boolean usaMaximosMinimos;
	private Boolean visto;
	private String elementosOcultos;
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Boolean getUsaCodigoBarras() {
		return usaCodigoBarras;
	}
	public void setUsaCodigoBarras(Boolean usaCodigoBarras) {
		this.usaCodigoBarras = usaCodigoBarras;
	}
	public Boolean getUsaUnidadAlterna() {
		return usaUnidadAlterna;
	}
	public void setUsaUnidadAlterna(Boolean usaUnidadAlterna) {
		this.usaUnidadAlterna = usaUnidadAlterna;
	}
	public Boolean getUsaMaximosMinimos() {
		return usaMaximosMinimos;
	}
	public void setUsaMaximosMinimos(Boolean usaMaximosMinimos) {
		this.usaMaximosMinimos = usaMaximosMinimos;
	}
	public Boolean getVisto() {
		return visto;
	}
	public void setVisto(Boolean visto) {
		this.visto = visto;
	}
	public String getElementosOcultos() {
		return elementosOcultos;
	}
	public void setElementosOcultos(String elementosOcultos) {
		this.elementosOcultos = elementosOcultos;
	}
	
}
