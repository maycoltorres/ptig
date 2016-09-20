package com.gamasoft.hps.sab.domain;
/**
 * 
 * @author wospino
 */
import java.util.Set;

import javax.persistence.Cache;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.VentaDto;
import com.gamasoft.hps.sab.dto.VentaGrupoSeleccionDto;

@Entity
@Table(name = "venta_gruposeleccion")
public class VentaGrupoSeleccion extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="cantidad", nullable=true)
	private Double cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_gruposeleccion", nullable = false)
    private GrupoSeleccion grupoSeleccion;
	
	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public GrupoSeleccion getGrupoSeleccion() {
		return grupoSeleccion;
	}

	public void setGrupoSeleccion(GrupoSeleccion grupoSeleccion) {
		this.grupoSeleccion = grupoSeleccion;
	}

	public VentaGrupoSeleccionDto toDto() {
		VentaGrupoSeleccionDto ventaGrupoDto = new VentaGrupoSeleccionDto();
		ventaGrupoDto.setCantidad(cantidad);
		
		if(venta != null){
			ventaGrupoDto.setIdVenta(venta.getId());
		}
		
		if(grupoSeleccion != null){
			ventaGrupoDto.setIdGrupoSeleccion(grupoSeleccion.getId());
		}
		
		return ventaGrupoDto;
	}
}
