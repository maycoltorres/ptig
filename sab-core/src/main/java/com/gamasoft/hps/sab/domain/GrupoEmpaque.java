package com.gamasoft.hps.sab.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.EmpaqueDto;
import com.gamasoft.hps.sab.dto.GrupoEmpaqueDto;
/**
 * 
 * @author emora
 */
@Entity
@Table(name = "grupoempaque")
public class GrupoEmpaque extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
    @Column(name="nombre", nullable=true, length=45)
	public String nombre;
    
    @Column(name = "activo", nullable = false, length = 1)
	private Boolean activo;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grupoEmpaque")
    private Set<Empaque> empaques;
    
    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;
	
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="grupoempaque_punto",joinColumns={@JoinColumn(name="id_grupoempaque")},inverseJoinColumns={@JoinColumn(name="id_punto")})
	private Set<Punto> puntos;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="grupoempaque_canal",joinColumns={@JoinColumn(name="id_grupoempaque")},inverseJoinColumns={@JoinColumn(name="id_canal")})
	private Set<Canal> canales;
    
    @Column(name = "predeterminado", nullable = false, length = 1)
	private Boolean predeterminado;
    
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public GrupoEmpaque(){ 
	}
	
	public GrupoEmpaque(GrupoEmpaqueDto dto) {
		this.nombre = dto.getNombre();
		this.activo = dto.getActivo();
		this.predeterminado = dto.getPredeterminado();
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public Set<Empaque> getEmpaques() {
		return empaques;
	}

	public void setEmpaques(Set<Empaque> empaques) {
		this.empaques = empaques;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Set<Punto> getPuntos() {
		return puntos;
	}

	public void setPuntos(Set<Punto> puntos) {
		this.puntos = puntos;
	}

	public Set<Canal> getCanales() {
		return canales;
	}

	public void setCanales(Set<Canal> canales) {
		this.canales = canales;
	}
	
	public Boolean getPredeterminado() {
		return predeterminado;
	}

	public void setPredeterminado(Boolean predeterminado) {
		this.predeterminado = predeterminado;
	}

	public GrupoEmpaqueDto toDto() {
		GrupoEmpaqueDto dto = new GrupoEmpaqueDto();
		dto.setId(this.getId());
		dto.setNombre(this.getNombre());
		dto.setActivo(this.getActivo());
		dto.setPredeterminado(this.getPredeterminado());
		
		if(articulo != null){
			dto.setIdArticulo(articulo.getId());
		}
		
		if(empaques != null){
			Set<EmpaqueDto> empaquesDto = new HashSet<EmpaqueDto>();
			for(Empaque e : empaques){
				empaquesDto.add(e.toDto());
			}
			dto.setEmpaques(empaquesDto);
		}

		return dto;
	}
	
	
	 	 
}
