package com.gamasoft.hps.sab.dto;
/**
 * 
 * @author wospino
 */
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gamasoft.hps.sab.domain.Inventario;
import com.gamasoft.hps.sab.domain.Punto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GrupoSeleccionDto {
	
	public static final Long INCREMENTO_PRECIO_POR_ARTICULO = 1L;
	public static final Long INCREMENTO_PRECIO_POR_GRUPO = 2L;
	public static final Long MAYOR_VALOR = 3L;
	public static final Long NO_APLICA = 4L;
	
	public static final Long TIPO_GRUPO_ABIERTO = 1L;
	public static final Long TIPO_GRUPO_CERRADO = 2L;
	
	private Long id;
	private String nombre;
	private Long caracteristicaPrecio;
	private Long idCliente;
	private Long tipoGrupo;
	private boolean activo;
	private Double incrementoPrecio;
	
	private Set<SeleccionDto> articulos;
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
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
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long id_cliente) {
		this.idCliente = id_cliente;
	}
	public Set<SeleccionDto> getArticulos() {
		return articulos;
	}
	public void setArticulos(Set<SeleccionDto> articulos) {
		this.articulos = articulos;
	}
	public Long getCaracteristicaPrecio() {
		return caracteristicaPrecio;
	}
	public void setCaracteristicaPrecio(Long caracteristicaPrecio) {
		this.caracteristicaPrecio = caracteristicaPrecio;
	}
	public Long getTipoGrupo() {
		return tipoGrupo;
	}
	public void setTipoGrupo(Long tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	public Double getIncrementoPrecio() {
		return incrementoPrecio;
	}
	public void setIncrementoPrecio(Double incrementoPrecio) {
		this.incrementoPrecio = incrementoPrecio;
	}

}
