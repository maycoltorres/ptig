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
public class MaximosMinimosDto {

	private static final long serialVersionUID = 1L;
	
	private long idPunto;
	
	private String nombrePunto;
	
	private long idInventario;
	
	private double minimo;
	
	private double maximo;
	
	public long getIdPunto() {
		return idPunto;
	}

	public void setIdPunto(long idPunto) {
		this.idPunto = idPunto;
	}

	public long getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(long idInventario) {
		this.idInventario = idInventario;
	}

	public double getMinimo() {
		return minimo;
	}

	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public void setMaximo(double maximo) {
		this.maximo = maximo;
	}

	public MaximosMinimosDto(){
		
	}

	public String getNombrePunto() {
		return nombrePunto;
	}

	public void setNombrePunto(String nombrePunto) {
		this.nombrePunto = nombrePunto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idInventario ^ (idInventario >>> 32));
		result = prime * result + (int) (idPunto ^ (idPunto >>> 32));
		long temp;
		temp = Double.doubleToLongBits(maximo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minimo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nombrePunto == null) ? 0 : nombrePunto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaximosMinimosDto other = (MaximosMinimosDto) obj;
		if (idInventario != other.idInventario)
			return false;
		if (idPunto != other.idPunto)
			return false;

		return true;
	}
	
	
	
}
