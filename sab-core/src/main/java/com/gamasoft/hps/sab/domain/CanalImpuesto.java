package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.CanalImpuestoDto;

/**
 *
 * @author wospino
 */
@Entity
@Table(name = "clientecanalimpuesto")
public class CanalImpuesto extends Persistent{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "predeterminado", nullable = true)
	private Boolean predeterminado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_canal", nullable = true)
	private Canal canal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_regimen", nullable = false)
	private Regimen regimen;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "id_tarifa", nullable = true)
	private Tarifa tarifa;
	
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}
	
	public Regimen getRegimen() {
		return regimen;
	}

	public void setRegimen(Regimen regimen) {
		this.regimen = regimen;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public CanalImpuesto(){
		
	}
	
	public CanalImpuesto(CanalImpuestoDto dto){
		this.predeterminado = dto.getPredeterminado();
	}
	
	public Boolean getPredeterminado() {
		return predeterminado;
	}

	public void setPredeterminado(Boolean predeterminado) {
		this.predeterminado = predeterminado;
	}

	public CanalImpuestoDto toDto() {
		CanalImpuestoDto dto = new CanalImpuestoDto();
		
		dto.setPredeterminado(predeterminado);
		dto.setId(getId());
		dto.setIdCliente(cliente.getId());
		
		if(canal != null){
			dto.setIdCanal(canal.getId());
			dto.setCanal(canal.getNombre());
		}
		
		if(regimen != null){
			dto.setIdRegimen(regimen.getId());
			dto.setRegimen(regimen.getNombre());
			
			if(regimen.getImpuesto() != null){
				dto.setIdImpuesto(regimen.getImpuesto().getId());
				dto.setImpuesto(regimen.getImpuesto().getNombre());
			}
		}
		
		if(tarifa != null){
			dto.setIdTarifa(tarifa.getId());
			dto.setTarifa(tarifa.getValor());
		}

		return dto;
	}
}
