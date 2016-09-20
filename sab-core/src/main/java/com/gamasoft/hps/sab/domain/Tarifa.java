package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.TarifaDto;
/**
 * 
 * @author wospino
 */
@Entity
@Table(name = "tarifa")
public class Tarifa extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
    @Column(name="valor", nullable=false)
	public Double valor;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_regimen", nullable = false)
    private Regimen regimen;
    
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Tarifa(){ 
	}
	
	public Tarifa(TarifaDto dto) {
		this.valor = dto.getValor();
	}
	
	public Regimen getRegimen() {
		return regimen;
	}

	public void setRegimen(Regimen regimen) {
		this.regimen = regimen;
	}

	public TarifaDto toDto() {
		TarifaDto dto = new TarifaDto();
		dto.setId(getId());
		dto.setValor(this.getValor());
		
		return dto;
	}
	 	 
}
