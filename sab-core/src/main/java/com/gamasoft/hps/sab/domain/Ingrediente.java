package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.IngredienteDto;
/**
 * 
 * @author emora
 */
@Entity
@Table(name = "ingrediente")
public class Ingrediente extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="cantidad")
	public Integer cantidad;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_articulo", nullable = false)
    private Articulo articulo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidad", nullable = false)
    private Unidad unidad;
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
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

	public IngredienteDto toDto() {
		IngredienteDto dto = new IngredienteDto();
		dto.setId(this.getId());
		dto.setCantidad(this.getCantidad());
		dto.setIdArticulo(this.getArticulo().getId());
		
		return dto;
	}
	 	 
}
