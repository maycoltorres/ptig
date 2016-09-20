package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.MunicipioDto;

/**
 * @author wospino
 */
@Entity
@Table(name = "municipio")

public class Municipio extends Persistent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="nombre", nullable=true, length=30)
	private String nombre;
	
	@Column(name="departamento_id", nullable=true, length=11)
	private Long departamento_id;


	/*******Contructor por defecto**************/
	Municipio(){
		
	}
	
	public Municipio(MunicipioDto dto){
		this.nombre=dto.getNombre();
		this.departamento_id=dto.getDepartamento_id();
	}
	
	public MunicipioDto toDto(){
		MunicipioDto dto=new MunicipioDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setDepartamento_id(this.departamento_id);;
		return dto;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getDepartamento_id() {
		return departamento_id;
	}

	public void setDepartamento_id(Long departamento_id) {
		this.departamento_id = departamento_id;
	}
	
	
	
}
