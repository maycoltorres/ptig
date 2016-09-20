package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.EmpaqueDto;
/**
 * 
 * @author emora
 */
@Entity
@Table(name = "empaque")
public class Empaque extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	@Column(name="cantidad", nullable = true, length = 11)
	public Long cantidad;
	
	@Column(name = "activo", nullable = false, length = 1)
	private Boolean activo;
	
	@ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;
	
	@ManyToOne
    @JoinColumn(name = "id_unidad")
    private Unidad unidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grupoempaque", nullable = false)
    private GrupoEmpaque grupoEmpaque;
    
	public Empaque(){ 
	}
	
	public Empaque(EmpaqueDto dto) {
		
	}
	
	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public GrupoEmpaque getGrupoEmpaque() {
		return grupoEmpaque;
	}

	public void setGrupoEmpaque(GrupoEmpaque grupoEmpaque) {
		this.grupoEmpaque = grupoEmpaque;
	}

	public EmpaqueDto toDto() {
		EmpaqueDto dto = new EmpaqueDto();
		dto.setId(getId());
		dto.setCantidad(cantidad);
		if(articulo != null){
			dto.setIdArticulo(articulo.getId());
			dto.setNombreArticulo(articulo.getNombre());
		}
		if(unidad != null){
			dto.setIdUnidad(unidad.getId());
			dto.setNombreUnidad(unidad.getNombre());
			dto.setTipoUnidad(unidad.getTipo());
		}
		
		return dto;
	}
	 	 
}
