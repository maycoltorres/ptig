package com.gamasoft.hps.sab.dto;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gamasoft.hps.sab.domain.RolCliente;
/**
*
* @author wospino
*/
@JsonIgnoreProperties(ignoreUnknown=true)
public class RolDto {
	
	private String Nombre;
	private Set<RolCliente> rolcliente;
	private boolean activo;
	private long id;	
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;
	
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Set<RolCliente> getRolcliente() {
		return rolcliente;
	}

	public void setRolcliente(Set<RolCliente> rolcliente) {
		this.rolcliente = rolcliente;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
}
