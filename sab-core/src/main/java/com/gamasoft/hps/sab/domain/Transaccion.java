package com.gamasoft.hps.sab.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.TransaccionDto;


@Entity
@Table(name = "transaccion")
public class Transaccion  extends Persistent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name="nombre", nullable=true, length=30)
	private String nombre;
	
	@Column(name="url", nullable=true, length=100)
	private String url;

	@Column(name="operacion", nullable=true,length=10)
	private String operacion;
	
	@ManyToMany(mappedBy="transaccion")
    private Set<RolCliente> Rolcliente = new HashSet<RolCliente>();
	
	@ManyToMany(mappedBy="transac")
    private Set<Rol> roles = new HashSet<Rol>();
	
	Transaccion(){
		
	}
	
	public TransaccionDto toDto(){
		TransaccionDto dto = new TransaccionDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setOperacion(this.operacion);
		dto.setUrl(this.url);
		
		return dto;
	}	
	
	public Transaccion (TransaccionDto dto){
		this.nombre=dto.getNombre();
		this.url=dto.getUrl();
		this.url=dto.getOperacion();
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<RolCliente> getRolcliente() {
		return Rolcliente;
	}

	public void setRolcliente(Set<RolCliente> rolcliente) {
		Rolcliente = rolcliente;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	
	
	
}
