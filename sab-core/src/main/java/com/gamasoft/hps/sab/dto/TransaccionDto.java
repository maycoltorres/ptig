package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author wospino
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransaccionDto {

	private Long id;
	private String nombre;
	private String url;
	private String operacion;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public TransaccionDto toDto() {
		// TODO Auto-generated method stub
		TransaccionDto dto = new TransaccionDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setUrl(this.url);
		return null;
	}
	
	
	
}
