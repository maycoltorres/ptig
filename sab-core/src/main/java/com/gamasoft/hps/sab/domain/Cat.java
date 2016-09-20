package com.gamasoft.hps.sab.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.CatDto;
import com.gamasoft.hps.sab.dto.CanalImpuestoDto;

/**
 *
 * @author wospino
 */
@Entity
@Table(name = "cat")
public class Cat extends Persistent{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cic", nullable = false)
	private CanalImpuesto cic;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_articulo", nullable = false)
	private Articulo articulo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tarifa", nullable = false)
	private Tarifa tarifa;
	
	public Cat(){
		
	}
	
	public CatDto toDto() {
		CatDto dto = new CatDto();
		
		dto.setIdCic(cic.getId());
		dto.setIdArticulo(articulo.getId());
		dto.setIdTarifa(tarifa.getId());

		return dto;
	}

	public Cat(CanalImpuestoDto dto){
		
	}

}
