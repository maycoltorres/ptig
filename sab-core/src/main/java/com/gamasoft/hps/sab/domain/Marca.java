package com.gamasoft.hps.sab.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;

@Entity
@Table(name = "marca")
public class Marca extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="nombre", nullable = true, length=30)
	private String nombre;
	
	@Column(name="id_cliente", nullable=true, length=11)
	private Long idCliente;
	
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name="Activo", nullable =true, length=1)
	private boolean Activo;
	
	
	
	@ManyToMany(mappedBy="marcas")
    private Set<Punto> puntos = new HashSet<Punto>();
	
	
	Marca(){
		
	}
	
	public MarcaDto toDto(){
		MarcaDto dto = new MarcaDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setIdCliente(this.idCliente);
		dto.setActivo(this.isActivo());
		if(getPuntos()!=null){
			 List<PuntoDto> PtoDto = new ArrayList<PuntoDto>();
		// for(Punto c : getPuntos())
			 {
				 //  PtoDto.add(c.toDto());
			   }
			   dto.setPuntos(PtoDto);
		}
		return dto;
	}
	
	 public MarcaDto toDtoLess() {
			// TODO Auto-generated method stub
		MarcaDto dto = new MarcaDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setIdCliente(this.idCliente);
		dto.setActivo(this.isActivo());
		return dto;
		}
	
	 public Marca(MarcaDto dto){
			this.nombre=dto.getNombre();
			this.idCliente=dto.getIdCliente();
			this.Activo=dto.isActivo();
		}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return Activo;
	}

	public void setActivo(boolean activo) {
		Activo = activo;
	}

	public Set<Punto> getPuntos() {
		return puntos;
	}

	public void setPuntos(Set<Punto> puntos) {
		this.puntos = puntos;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
		
}
