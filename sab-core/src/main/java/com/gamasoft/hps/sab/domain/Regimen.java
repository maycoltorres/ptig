package com.gamasoft.hps.sab.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.ImpuestoDto;
import com.gamasoft.hps.sab.dto.RegimenDto;
import com.gamasoft.hps.sab.dto.TarifaDto;
/**
 * 
 * @author wospino
 */
@Entity
@Table(name = "regimen")
public class Regimen extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
    @Column(name="nombre", nullable=true, length=30)
	public String nombre;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "regimen")
    private Set<Tarifa> tarifas;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_impuesto", nullable = false)
    private Impuesto impuesto;
    
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Impuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}

	public Regimen(){ 
	}
	
	public Regimen(ImpuestoDto dto) {
		this.nombre = dto.getNombre();
	}

	public RegimenDto toDto() {
		RegimenDto dto = new RegimenDto();
		dto.setId(this.getId());
		dto.setNombre(nombre);
		
		if(tarifas != null){
			Set<TarifaDto> tarifasDto = new HashSet<TarifaDto>();
			for(Tarifa t : tarifas){
				tarifasDto.add(t.toDto());
			}
			dto.setTarifas(tarifasDto);
		}
		
		return dto;
	}
	 	 
}
