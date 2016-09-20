package com.gamasoft.hps.sab.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.IngredienteDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.dto.RecetaDto;
/**
 * 
 * @author wospino
 */
@Entity
@Table(name = "receta")
public class Receta extends Persistent {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="nombre", nullable=true, length=45)
	private String nombre;
	
	@Column(name="preparacion", nullable=true, length=2000)
	private String preparacion;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="activo")
	private Boolean activo;
	
	@Column(name="predeterminada")
	private Boolean predeterminada;
	
	
	@ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;
	
	@ManyToOne
    @JoinColumn(name = "id_unidad")
    private Unidad unidad;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="receta_canal",joinColumns={@JoinColumn(name="id_receta")},inverseJoinColumns={@JoinColumn(name="id_canal")})
	private Set<Canal> canales;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="receta_punto",joinColumns={@JoinColumn(name="id_receta")},inverseJoinColumns={@JoinColumn(name="id_punto")})
	private Set<Punto> puntos;
	
	@OneToMany(mappedBy = "receta", cascade = {CascadeType.ALL})
    private Set<Ingrediente> ingredientes;
	
	public Receta(){
		
	}
	
	public Receta(RecetaDto r){
		this.setNombre(r.getNombre());
		this.setPreparacion(r.getPreparacion());
		this.setCantidad(r.getCantidad());
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	public Articulo getArticulo() {
		return articulo;
	}
	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	
	

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public Boolean getPredeterminada() {
		return predeterminada;
	}

	public void setPredeterminada(Boolean predeterminada) {
		this.predeterminada = predeterminada;
	}
	
	

	public Set<Canal> getCanales() {
		return canales;
	}

	public void setCanales(Set<Canal> canales) {
		this.canales = canales;
	}

	public Set<Punto> getPuntos() {
		return puntos;
	}

	public void setPuntos(Set<Punto> puntos) {
		this.puntos = puntos;
	}

	public Set<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Set<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public RecetaDto toDto() {
		RecetaDto receta = new RecetaDto();
		
		receta.setId(this.getId());
		receta.setNombre(this.getNombre());
		receta.setIdArticulo(articulo.getId());
		receta.setPreparacion(this.getPreparacion());
		receta.setIdUnidad(unidad.getId());
		receta.setCantidad(this.getCantidad());
		receta.setActivo(this.getActivo());
		receta.setPredeterminada(this.getPredeterminada());
				
		return receta;
			
	}
	
	public RecetaDto toFullDto() {
		RecetaDto receta = toDto();
		
		if(this.getPuntos() != null){
			List<PuntoDto> puntosDto = new ArrayList<PuntoDto>();
			for(Punto p : this.getPuntos()){
				puntosDto.add(p.toDto());
			}
			receta.setPuntos(puntosDto);
		}
		
		if(this.getCanales() != null){
			List<CanalDto> canalesDto = new ArrayList<CanalDto>();
			for(Canal c : this.getCanales()){
				canalesDto.add(c.toDto());
			}
			receta.setCanales(canalesDto);
		}
		
		if(this.getIngredientes() != null){
			List<IngredienteDto> ingredientesDto = new ArrayList<IngredienteDto>();
			for(Ingrediente i : this.getIngredientes()){
				ingredientesDto.add(i.toDto());
			}
			receta.setIngredientes(ingredientesDto);
		}
			
		return receta;
			
	}
	 	 
}
