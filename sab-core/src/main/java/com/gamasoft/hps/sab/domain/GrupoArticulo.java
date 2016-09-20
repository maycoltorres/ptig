package com.gamasoft.hps.sab.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.GrupoDto;
import com.gamasoft.hps.sab.dto.GrupoArticuloDto;
/**
 * 
 * @author wospino
 */
@Entity
@Table(name = "grupoarticulo")
public class GrupoArticulo extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	@Column(name="nombre", nullable=true, length=30)
	public String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clasearticulo", nullable = false)
    private ClaseArticulo claseArticulo;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ClaseArticulo getClaseArticulo() {
		return claseArticulo;
	}

	public void setClaseArticulo(ClaseArticulo claseArticulo) {
		this.claseArticulo = claseArticulo;
	}

	public GrupoArticulo(){ 
	}
	
	public GrupoArticulo(GrupoArticuloDto dto) {
		this.nombre = dto.getNombre();
	}

	public GrupoArticuloDto toDto() {
		GrupoArticuloDto dto = new GrupoArticuloDto();
		dto.setId(this.getId());
		//dto.setIdClaseArticulo(this.claseArticulo.getId());
		dto.setNombre(this.getNombre());
		
		return dto;
	}
	 	 
}
