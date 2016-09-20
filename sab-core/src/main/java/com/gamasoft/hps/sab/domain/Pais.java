package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.Validate;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.PaisDto;

@Entity
@Table(name = "pais")
public class Pais extends Persistent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="nombre", nullable=true, length=40)
	private String nombre;

//	@Column(name="id", nullable = true, length=40)
//	private Long id;
	
	public Pais(PaisDto dto) {
		Validate.notNull(dto.getNombre(), "Name cannot be null");
		this.nombre = dto.getNombre();
//		this.id=dto.getId();
		
	}

	public PaisDto toDto(){
		PaisDto dto = new PaisDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		return dto;
		
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	


	Pais(){
		
	}
	
	
}
