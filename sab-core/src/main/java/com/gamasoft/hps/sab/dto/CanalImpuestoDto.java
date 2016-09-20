package com.gamasoft.hps.sab.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.Validate;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gamasoft.hps.sab.domain.base.AuditablePersistent;
import com.gamasoft.hps.sab.domain.base.Persistent;

/**
 *
 * @author wospino
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CanalImpuestoDto {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Boolean predeterminado;
	private Long idCanal;
	private Long idCliente;
	private Long idImpuesto;
	private Long idRegimen;
	private Long idTarifa;
	
	private String canal;
	private String impuesto;
	private String regimen;
	private Double tarifa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCanal() {
		return idCanal;
	}
	public void setIdCanal(Long idCanal) {
		this.idCanal = idCanal;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdImpuesto() {
		return idImpuesto;
	}
	public void setIdImpuesto(Long idImpuesto) {
		this.idImpuesto = idImpuesto;
	}
	public Long getIdRegimen() {
		return idRegimen;
	}
	public void setIdRegimen(Long idRegimen) {
		this.idRegimen = idRegimen;
	}
	public Long getIdTarifa() {
		return idTarifa;
	}
	public void setIdTarifa(Long idTarifa) {
		this.idTarifa = idTarifa;
	}
	public Boolean getPredeterminado() {
		return predeterminado;
	}
	public void setPredeterminado(Boolean predeterminado) {
		this.predeterminado = predeterminado;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public Double getTarifa() {
		return tarifa;
	}
	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}
	
}
