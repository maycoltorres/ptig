package com.gamasoft.hps.sab.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
@Table(name = "impuesto")
public class Impuesto extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
    @Column(name="nombre", nullable=true, length=30)
	public String nombre;
    
    @Column(name="id_pais", nullable=false)
	public Long idPais;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "impuesto")
    private Set<Regimen> regimenes;
    
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}
	
	public Set<Regimen> getRegimenes() {
		return regimenes;
	}

	public void setRegimenes(Set<Regimen> regimenes) {
		this.regimenes = regimenes;
	}

	public Impuesto(){ 
	}
	
	public Impuesto(ImpuestoDto dto) {
		this.nombre = dto.getNombre();
	}

	public ImpuestoDto toDto() {
		ImpuestoDto dto = new ImpuestoDto();
		dto.setId(this.getId());
		dto.setNombre(nombre);
		dto.setIdPais(idPais);
		
		if(regimenes != null){
			Set<RegimenDto> regimenesDto = new HashSet<RegimenDto>();
			for(Regimen r : regimenes){
				regimenesDto.add(r.toDto());
			}
			dto.setRegimenes(regimenesDto);
		}
		
		return dto;
	}
	 	 
}
