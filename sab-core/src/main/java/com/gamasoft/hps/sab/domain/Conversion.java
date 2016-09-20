package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.Validate;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.ConversionDto;
import com.gamasoft.hps.sab.dto.UnidadDto;

@Entity
@Table(name = "conversion")
public class Conversion extends Persistent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="factor", nullable=false)
	private Double factor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_unidad_origen", nullable = false)
	private Unidad unidadOrigen;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_unidad_destino", nullable = false)
	private Unidad unidadDestino;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_articulo", nullable = false)
	private Articulo articulo;

	public Conversion() {
		
	}

	public Conversion(ConversionDto dto) {
		Validate.notNull(dto.getFactor(), "Factor cannot be null");
		
	}

	public ConversionDto toDto(){
		ConversionDto dto = new ConversionDto();
		
		dto.setId(this.getId());
		dto.setIdUnidadOrigen(unidadOrigen.getId());
		dto.setIdUnidadDestino(unidadDestino.getId());
		if(articulo != null){
			dto.setIdArticulo(articulo.getId());
		}
		dto.setFactor(factor);
		
		return dto;
		
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Unidad getUnidadOrigen() {
		return unidadOrigen;
	}

	public void setUnidadOrigen(Unidad unidadOrigen) {
		this.unidadOrigen = unidadOrigen;
	}

	public Unidad getUnidadDestino() {
		return unidadDestino;
	}

	public void setUnidadDestino(Unidad unidadDestino) {
		this.unidadDestino = unidadDestino;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	

}
