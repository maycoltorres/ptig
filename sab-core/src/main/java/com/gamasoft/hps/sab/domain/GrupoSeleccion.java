package com.gamasoft.hps.sab.domain;
/**
 * 
 * @author wospino
 */
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.GrupoSeleccionDto;
import com.gamasoft.hps.sab.dto.SeleccionDto;

@Entity
@Table(name = "gruposeleccion")
public class GrupoSeleccion extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nombre", nullable = true, length = 60)
	private String nombre;

	@Column(name = "caracteristicaprecio", nullable = false)
	private Long caracteristicaPrecio;
	
	@Column(name = "id_cliente", nullable = false)
	private Long id_cliente;
	
	@Column(name = "tipogrupo", nullable = false)
	private Long tipoGrupo;
	
	@Column(name="activo", nullable=true, length=1)
	private boolean activo;
	
	@Column(name = "incrementoPrecio", nullable = true)
	private Double incrementoPrecio;
		
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "grupoSeleccion")
    private Set<Seleccion> selecciones;
    
	
	public GrupoSeleccion() {

	}

	public GrupoSeleccion(GrupoSeleccionDto dto) {

		this.nombre = dto.getNombre();
		this.id_cliente = dto.getIdCliente();
		this.caracteristicaPrecio = dto.getCaracteristicaPrecio();
		this.activo = dto.isActivo();
		this.tipoGrupo = dto.getTipoGrupo();
		this.incrementoPrecio = dto.getIncrementoPrecio();
	}

	public GrupoSeleccionDto toDto() {

		GrupoSeleccionDto dto = new GrupoSeleccionDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setCaracteristicaPrecio(caracteristicaPrecio);
		dto.setIdCliente(this.id_cliente);
		dto.setTipoGrupo(this.tipoGrupo);
		dto.setActivo(activo);
		dto.setIncrementoPrecio(this.incrementoPrecio);
	
		return dto;
	}
	
	public GrupoSeleccionDto toFullDto() {
		GrupoSeleccionDto dto = toDto();
		Set<SeleccionDto> articulosDto = 
				new HashSet<SeleccionDto>();
		
		for(Seleccion s : selecciones){
			articulosDto.add(s.toDto());
		}
		dto.setArticulos(articulosDto);
		
		return dto;
	}
	
	
	public Long getCaracteristicaPrecio() {
		return caracteristicaPrecio;
	}

	public void setCaracteristicaPrecio(Long caracteristicaPrecio) {
		this.caracteristicaPrecio = caracteristicaPrecio;
	}

	public Set<Seleccion> getSelecciones() {
		return selecciones;
	}

	public void setSelecciones(Set<Seleccion> selecciones) {
		this.selecciones = selecciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
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
