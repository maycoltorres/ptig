package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.ClaseVentaDto;
/**
 * 
 * @author wospino
 */
@Entity
@Table(name = "canal")
public class Canal extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
    @Column(name="nombre", nullable=true, length=30)
	public String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Canal(){ 
	}
	
	public Canal(CanalDto dto) {
		this.nombre = dto.getNombre();
	}

	public CanalDto toDto() {
		CanalDto dto = new CanalDto();
		dto.setId(this.getId());
		dto.setNombre(this.getNombre());
		
		return dto;
	}
	 	 
}
