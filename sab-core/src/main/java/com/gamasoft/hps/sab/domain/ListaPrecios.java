package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.ListaPreciosDto;
/**
 * 
 * @author emora
 */
@Entity
@Table(name = "lista_precios")
public class ListaPrecios extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
    @Column(name="nombre", nullable=true, length=30)
	public String nombre;
    
    @Column(name="activo")
	public Boolean activo;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ListaPrecios(){ 
	}
	
	public ListaPrecios(BodegaDto dto) {
		this.nombre = dto.getNombre();
	}
	
	public ListaPreciosDto toDto() {
		ListaPreciosDto dto = new ListaPreciosDto();
		dto.setId(this.getId());
		dto.setNombre(this.getNombre());
		dto.setActivo(activo);
		
		return dto;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	 	 
}
