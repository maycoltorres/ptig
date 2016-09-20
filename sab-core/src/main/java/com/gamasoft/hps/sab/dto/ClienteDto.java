/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author wospino
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDto {

	private Long id;
	private String nombre;
	private String nit;
	private int digitoChequeo;
	private String direccion;
	private String telefono;
	private int extension;
	private String email;
	private String celular;
	private String regimen_id;
	private String nombreRepresentante;
	private String documentoRepresentante;
	private String nombreDueno;
	private String mailDueno;
	private String celularDueno;
	private int municipio;
//	private String documentoDueno;
	private long telefono2;
//	private String contactoDnegocio;
//	private String contactoCargo;
//	private String contactoMail;
//	private String contactoCelular;
	private int extension2;
//	private int municipio_id;
//	private String nombreDueno1;
//	private String mailDueno1;
//	private String celularDueno1;
//	private String nombreDueno2;
//	private String mailDueno2;
//	private String celularDueno2;
//	private List<RolClienteDto> roles;
	
	
	/****************************** Getters and Setters de las variables dao **************************************/	
	private boolean activo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public int getDigitoChequeo() {
		return digitoChequeo;
	}
	public void setDigitoChequeo(int digitoChequeo) {
		this.digitoChequeo = digitoChequeo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getExtension() {
		return extension;
	}
	public void setExtension(int extension) {
		this.extension = extension;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getRegimen_id() {
		return regimen_id;
	}
	public void setRegimen_id(String regimen_id) {
		this.regimen_id = regimen_id;
	}
	public String getNombreRepresentante() {
		return nombreRepresentante;
	}
	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}
	
	public String getDocumentoRepresentante() {
		return documentoRepresentante;
	}
	public void setDocumentoRepresentante(String documentoRepresentante) {
		this.documentoRepresentante = documentoRepresentante;
	}
	public String getNombreDueno() {
		return nombreDueno;
	}
	public void setNombreDueno(String nombreDueno) {
		this.nombreDueno = nombreDueno;
	}

	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
//	public List<RolClienteDto> getRoles() {
//		return roles;
//	}
//	public void setRoles(List<RolClienteDto> roles) {
//		this.roles = roles;
//	}
	public String getMailDueno() {
		return mailDueno;
	}
	public void setMailDueno(String mailDueno) {
		this.mailDueno = mailDueno;
	}
	public String getCelularDueno() {
		return celularDueno;
	}
	public void setCelularDueno(String celularDueno) {
		this.celularDueno = celularDueno;
	}
	public int getMunicipio() {
		return municipio;
	}
	public void setMunicipio(int municipio) {
		this.municipio = municipio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(long telefono2) {
		this.telefono2 = telefono2;
	}
	public int getExtension2() {
		return extension2;
	}
	public void setExtension2(int extension2) {
		this.extension2 = extension2;
	}	
	
}