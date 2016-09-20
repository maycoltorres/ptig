package com.gamasoft.hps.sab.dto;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RegimenDto {

	private Long id;
	private String nombre;
	private Long idImpuesto;
	private String impuesto;
	private Long idPais;
	private String pais;
	
	private Set<TarifaDto> tarifas;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	
	public Long getIdImpuesto() {
		return idImpuesto;
	}

	public void setIdImpuesto(Long idImpuesto) {
		this.idImpuesto = idImpuesto;
	}

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Set<TarifaDto> getTarifas() {
		return tarifas;
	}

	public void setTarifas(Set<TarifaDto> tarifas) {
		this.tarifas = tarifas;
	}
}
