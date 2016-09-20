package com.gamasoft.hps.sab.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.ClaseVentaDto;
/**
 * 
 * @author emora
 */
@Entity
@Table(name = "bodega")
public class Bodega extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
    @Column(name="nombre", nullable=true, length=30)
	public String nombre;
    
    @Column(name="bodegainterna")
	public Boolean bodegaInterna;
    
    @Column(name = "activo", nullable = false, length = 1)
	private Boolean activo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_punto", nullable = false)
    private Punto punto;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Boolean getBodegaInterna() {
		return bodegaInterna;
	}

	public void setBodegaInterna(Boolean bodegaInterna) {
		this.bodegaInterna = bodegaInterna;
	}

	public Bodega(){ 
	}
	
	public Bodega(BodegaDto dto) {
		this.nombre = dto.getNombre();
		this.activo = dto.getActivo();
		this.bodegaInterna = dto.getBodegaInterna();
	}
	
	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public BodegaDto toDto() {
		BodegaDto dto = new BodegaDto();
		dto.setId(this.getId());
		dto.setNombre(this.getNombre());
		dto.setBodegaInterna(this.getBodegaInterna());
		dto.setActivo(this.getActivo());
		if (punto != null){
			//dto.setIdPunto(punto.getId());
		}
		
		return dto;
	}
	 	 
}
