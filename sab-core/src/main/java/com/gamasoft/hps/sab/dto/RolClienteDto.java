package com.gamasoft.hps.sab.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RolClienteDto {

	private long id;
	private Long id_cliente;
	private Long id_rol;
	private String nombre;
	private List<RolDto> roles;
	private boolean activo;
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public List<RolDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RolDto> roles) {
		this.roles = roles;
	}
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Long getId_rol() {
		return id_rol;
	}
	public void setId_rol(Long id_rol) {
		this.id_rol = id_rol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	
	

	
	
	
	
}
