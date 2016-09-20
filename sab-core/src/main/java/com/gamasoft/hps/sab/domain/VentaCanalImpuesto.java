package com.gamasoft.hps.sab.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.VentaCanalImpuestoDto;

/**
 *
 * @author wospino
 */
/**
 * @author usuario
 *
 */
@Entity
@Table(name = "ventacanalimpuesto")
public class VentaCanalImpuesto extends Persistent{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_venta", nullable = false)
	private Venta venta;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_canal", nullable = false)
	private Canal canal;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "id_tarifa", nullable = true)
	private Tarifa tarifa;
	
	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}
	
	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public VentaCanalImpuesto(){
		
	}
	
	public VentaCanalImpuesto(VentaCanalImpuestoDto dto){
		
	}
	
	public VentaCanalImpuestoDto toDto() {
		VentaCanalImpuestoDto dto = new VentaCanalImpuestoDto();
		
		dto.setId(getId());
		dto.setIdCanal(canal.getId());
		dto.setIdVenta(venta.getId());
		
		if(tarifa != null){
			dto.setIdTarifa(tarifa.getId());
			
			if(tarifa.getRegimen() != null){
				dto.setIdRegimen(tarifa.getRegimen().getId());
				
				if(tarifa.getRegimen().getImpuesto() != null){
					dto.setIdImpuesto(tarifa.getRegimen().getImpuesto().getId());
				}
			}
		}

		return dto;
	}
}
