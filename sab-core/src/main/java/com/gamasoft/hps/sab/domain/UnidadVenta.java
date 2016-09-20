package com.gamasoft.hps.sab.domain;
/**
 * 
 * @author wospino
 */
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.UnidadVentaDto;
import com.gamasoft.hps.sab.dto.VentaDto;

@Entity
@Table(name = "unidadventa")
public class UnidadVenta extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="valor")
	private Float valor;
	
	@ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;
	
	@ManyToOne
    @JoinColumn(name = "id_listaprecios")
    private ListaPrecios listaPrecios;
	
	@ManyToOne
    @JoinColumn(name = "id_unidad")
    private Unidad unidad;
	
	@Column(name="principal")
	private Boolean principal;
	
	public UnidadVenta() {
		
	}

	public UnidadVenta(UnidadVentaDto unidadVentaDto) {
		this.setValor(unidadVentaDto.getValor());
	}


	public Float getValor() {
		return valor;
	}




	public void setValor(Float valor) {
		this.valor = valor;
	}




	public Venta getVenta() {
		return venta;
	}




	public void setVenta(Venta venta) {
		this.venta = venta;
	}




	public ListaPrecios getListaPrecios() {
		return listaPrecios;
	}




	public void setListaPrecios(ListaPrecios listaPrecios) {
		this.listaPrecios = listaPrecios;
	}




	public Unidad getUnidad() {
		return unidad;
	}




	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public UnidadVentaDto toDto() {
		UnidadVentaDto unidadVenta = new UnidadVentaDto();
		unidadVenta.setValor(valor);
		unidadVenta.setPrincipal(principal);
		
		if(venta != null){
			unidadVenta.setIdVenta(venta.getId());
		}
		
		if(unidad != null){
			unidadVenta.setIdUnidad(unidad.getId());
		}
		
		if(listaPrecios!= null){
			unidadVenta.setIdListaPrecios(listaPrecios.getId());
		}
		
		return unidadVenta;
	}
}
