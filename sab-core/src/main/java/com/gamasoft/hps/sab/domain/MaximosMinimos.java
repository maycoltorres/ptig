package com.gamasoft.hps.sab.domain;

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

import com.gamasoft.hps.sab.domain.base.AuditablePersistent;
import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.MaximosMinimosDto;
import com.gamasoft.hps.sab.dto.RolClienteDto;

/**
 *
 * @author wospino
 */
@Entity
@Table(name = "maximosminimos")
public class MaximosMinimos extends Persistent{

	private static final long serialVersionUID = 1L;
	
	@Column(name="minimo", nullable=false)
	private Double minimo;
	
	@Column(name="maximo", nullable=false)
	private Double maximo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_punto", nullable = false)
	private Punto punto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_inventario", nullable = false)
	private Inventario inventario;
	
	
	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public MaximosMinimos(){
		
	}
	
	public MaximosMinimosDto toDto() {
		MaximosMinimosDto dto = new MaximosMinimosDto();
		
		dto.setIdInventario(this.inventario.getId());
		dto.setIdPunto(this.punto.getId());
		dto.setNombrePunto(this.getPunto().getNombre());
		dto.setMinimo(minimo);
		dto.setMaximo(maximo);

		return dto;
	}

	public MaximosMinimos(MaximosMinimosDto dto){
		this.maximo=dto.getMaximo();
		this.minimo=dto.getMinimo();
	}

}
