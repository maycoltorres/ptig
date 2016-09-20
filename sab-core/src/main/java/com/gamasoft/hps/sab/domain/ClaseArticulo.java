package com.gamasoft.hps.sab.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.ClaseArticuloDto;
import com.gamasoft.hps.sab.dto.GrupoArticuloDto;
/**
 * 
 * @author wospino
 */
@Entity
@Table(name = "clasearticulo")
public class ClaseArticulo extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	@Column(name="id_cliente", nullable=false)
	public Long idCliente;
	
	@Column(name="nombre", nullable=true, length=30)
	public String nombre;
	
    @Column(name="venta", nullable=false)
	public Boolean venta;
    
    @Column(name="inventario", nullable=false)
	public Boolean inventario;
    
    @Column(name="activo", nullable=false)
	public Boolean activo;
    
    @OneToMany(fetch = FetchType.LAZY, 
    		mappedBy = "claseArticulo",
    		cascade = {CascadeType.ALL},
    		orphanRemoval=true)
    private Set<GrupoArticulo> grupos;
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getVenta() {
		return venta;
	}

	public void setVenta(Boolean venta) {
		this.venta = venta;
	}

	public Boolean getInventario() {
		return inventario;
	}

	public void setInventario(Boolean inventario) {
		this.inventario = inventario;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	

	public Set<GrupoArticulo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<GrupoArticulo> grupos) {
		this.grupos = grupos;
	}

	public ClaseArticulo(){ 
	}
	
	public ClaseArticulo(ClaseArticuloDto dto) {
		this.nombre = dto.getNombre();
		this.venta = dto.getVenta();
		this.inventario = dto.getInventario();
		this.activo = dto.getActivo();
	
	}

	public ClaseArticuloDto toDto() {
		ClaseArticuloDto dto = new ClaseArticuloDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setVenta(venta);
		dto.setInventario(inventario);
		dto.setActivo(activo);
		
		if(Hibernate.isInitialized(grupos)){
			Set<GrupoArticuloDto> gruposDto = new HashSet<GrupoArticuloDto>();
			for(GrupoArticulo g : grupos){
				gruposDto.add(g.toDto());
			}
			dto.setGrupos(gruposDto);
		}
		
		return dto;
	}
	 	 
}
