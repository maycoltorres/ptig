package com.gamasoft.hps.sab.dto;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author usuario
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GrupoEmpaqueDto {

	private Long id;
	private Long idArticulo;
	private String nombre;
	private Boolean activo;
	private Boolean predeterminado;
	private Set<EmpaqueDto> empaques;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public Long getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Set<EmpaqueDto> getEmpaques() {
		return empaques;
	}

	public void setEmpaques(Set<EmpaqueDto> empaques) {
		this.empaques = empaques;
	}

	public Boolean getPredeterminado() {
		return predeterminado;
	}

	public void setPredeterminado(Boolean predeterminado) {
		this.predeterminado = predeterminado;
	}
	
}
