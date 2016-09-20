package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.DepartamentoDto;
/**
 * @author wospino
 */
@Entity
@Table(name = "departamento")

public class Departamento extends Persistent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="nombre", nullable = true, length=40)
	private String nombre;
	@Column(name="pais_id", nullable = true, length=11)
	private Long pais_id;
	
	/*********contructor por default**********/
	Departamento(){
		
	}
	
	public Departamento(DepartamentoDto dto){
		this.nombre= dto.getNombre();	
		
	}
	
	public DepartamentoDto toDto(){
		DepartamentoDto dto = new DepartamentoDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setPais_id(this.pais_id);
		return dto;
	}
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getPais_id() {
		return pais_id;
	}

	public void setPais_id(Long pais_id) {
		this.pais_id = pais_id;
	}
	

	
}
