package com.gamasoft.hps.sab.domain;
/**
 * 
 * @author wospino
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.SeleccionDto;

@Entity
@Table(name = "seleccion")
public class Seleccion extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "cantidadADescargar", nullable = true)
	private Double cantidadADescargar;

	@Column(name = "incrementoprecio", nullable = true)
	private Double incrementoPrecio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gruposeleccion", nullable = false)
    private GrupoSeleccion grupoSeleccion;
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_articulo", nullable = false)
    private Articulo articulo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_unidad", nullable = false)
    private Unidad unidad;
	
	
	public Double getCantidadADescargar() {
		return cantidadADescargar;
	}

	public void setCantidadADescargar(Double cantidadADescargar) {
		this.cantidadADescargar = cantidadADescargar;
	}

	public Double getIncrementoPrecio() {
		return incrementoPrecio;
	}

	public void setIncrementoPrecio(Double incrementoPrecio) {
		this.incrementoPrecio = incrementoPrecio;
	}

	public Seleccion() {

	}
	

	public GrupoSeleccion getGrupoSeleccion() {
		return grupoSeleccion;
	}

	public void setGrupoSeleccion(GrupoSeleccion grupoSeleccion) {
		this.grupoSeleccion = grupoSeleccion;
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

	public Seleccion(SeleccionDto dto) {

		this.cantidadADescargar = dto.getCantidadADescargar();
		this.incrementoPrecio = dto.getIncrementoPrecio();
		
	}

	public SeleccionDto toDto() {

		SeleccionDto dto = new SeleccionDto();
		dto.setId(this.getId());
		dto.setCantidadADescargar(cantidadADescargar);
		dto.setIncrementoPrecio(incrementoPrecio);
		
		if(articulo!=null){
			dto.setIdArticulo(articulo.getId());
			dto.setNombreArticulo(articulo.getNombre());
		}
		
		if(unidad != null){
			dto.setIdUnidad(unidad.getId());
			dto.setNombreUnidad(unidad.getNombre());
		}
		
		return dto;
	}
	
}
