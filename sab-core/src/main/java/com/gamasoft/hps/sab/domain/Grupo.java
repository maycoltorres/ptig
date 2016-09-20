package com.gamasoft.hps.sab.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.GrupoDto;
/**
 * 
 * @author wospino
 */
@Entity
@Table(name = "grupo")
public class Grupo extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
    @Column(name="nombre", nullable=true, length=30)
	public String nombre;
	@Column(name="Activo", nullable=true, length=1)
	public Boolean Activo;
	
	
	public Grupo(){ 
	 }
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="cliente_grupo",joinColumns={@JoinColumn(name="id_grupo")},inverseJoinColumns={@JoinColumn(name="id_cliente")})
	private Set<Cliente> clientes;
	
	
	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Grupo(GrupoDto dto) {
	        this.nombre = dto.getNombre();
	    }

	 public GrupoDto toDto() {
			// TODO Auto-generated method stub
		GrupoDto dto = new GrupoDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		if(getClientes()!=null){
			 List<ClienteDto> clDto = new ArrayList<ClienteDto>();
			   for(Cliente c : getClientes()){
			   clDto.add(c.toDto());
			   }
			   dto.setClientes(clDto);
		}
		return dto;
		}
	 
	 public GrupoDto toDtoLess() {
			// TODO Auto-generated method stub
		GrupoDto dto = new GrupoDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		return dto;
		}
	 
	 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getActivo() {
		return Activo;
	}

	public void setActivo(Boolean activo) {
		this.Activo = activo;
	}

	

	



	
}
