package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.Validate;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.UnidadDto;

@Entity
@Table(name = "unidad")
public class Unidad extends Persistent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="nombre", nullable=true, length=40)
	private String nombre;

	@Column(name="estandar", nullable=true, length=40)
	private Boolean estandar;
	
	@Column(name="id_cliente", nullable=true, length=40)
	private Long idCliente;
	
	@Column(name="tipo", nullable=true, length=45)
	private String tipo;

	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long id_cliente) {
		this.idCliente = id_cliente;
	}
	
	public Unidad() {
		
	}

	public Unidad(UnidadDto dto) {
		Validate.notNull(dto.getNombre(), "Name cannot be null");
		
		this.nombre = dto.getNombre();
		this.estandar = dto.getEstandar();
		this.idCliente=dto.getIdCliente();
		this.tipo = dto.getTipo();
	}

	public UnidadDto toDto(){
		UnidadDto dto = new UnidadDto();
		
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setEstandar(this.getEstandar());
		dto.setIdCliente(this.getIdCliente());
		dto.setTipo(this.getTipo());
		
		return dto;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getEstandar() {
		return estandar;
	}

	public void setEstandar(Boolean estandar) {
		this.estandar = estandar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
		
}
