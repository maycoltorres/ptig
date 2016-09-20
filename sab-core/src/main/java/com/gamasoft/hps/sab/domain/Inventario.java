package com.gamasoft.hps.sab.domain;
/**
 * 
 * @author wospino
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.ArticuloDto;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.InventarioDto;
import com.gamasoft.hps.sab.dto.MaximosMinimosDto;
import com.gamasoft.hps.sab.dto.RecetaDto;
import com.gamasoft.hps.sab.dto.UnidadDto;

@Entity
@Table(name = "inventario")
public class Inventario extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "aliasunidad", nullable = true, length = 50)
	private String aliasUnidad;
	
	@OneToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;
	
	@OneToOne
    @JoinColumn(name = "id_unidad_kardex")
    private Unidad unidadKardex;
	
	@OneToOne
    @JoinColumn(name = "id_unidad_alterna")
    private Unidad unidadAlterna;
	
	@OneToOne
    @JoinColumn(name = "id_grupoarticulo")
    private GrupoArticulo grupoArticulo;
	
	@OneToMany(cascade= {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "inventario", orphanRemoval=true )
    private Set<MaximosMinimos> maximosYMinimos;
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name="inventario_unidadcompra",joinColumns={@JoinColumn(name="id_inventario")},inverseJoinColumns={@JoinColumn(name="id_unidad")})
	private Set<Unidad> unidadesCompra;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="inventario_bodega",joinColumns={@JoinColumn(name="id_inventario")},inverseJoinColumns={@JoinColumn(name="id_bodega")})
	private Set<Bodega> bodegas;
	
	public Set<MaximosMinimos> getMaximosYMinimos() {
		return maximosYMinimos;
	}

	public void setMaximosYMinimos(Set<MaximosMinimos> maximosYMinimos) {
		this.maximosYMinimos = maximosYMinimos;
	}
	
	public Set<Unidad> getUnidadesCompra() {
		return unidadesCompra;
	}

	public void setUnidadesCompra(Set<Unidad> unidadesCompra) {
		this.unidadesCompra = unidadesCompra;
	}

	public Articulo getArticulo() {
		return articulo;
	}
	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public Unidad getUnidadKardex() {
		return unidadKardex;
	}

	public void setUnidadKardex(Unidad unidadKardex) {
		this.unidadKardex = unidadKardex;
	}

	public Unidad getUnidadAlterna() {
		return unidadAlterna;
	}

	public void setUnidadAlterna(Unidad unidadAlterna) {
		this.unidadAlterna = unidadAlterna;
	}
	
	public Set<Bodega> getBodegas() {
		return bodegas;
	}

	public void setBodegas(Set<Bodega> bodegas) {
		this.bodegas = bodegas;
	}

	public GrupoArticulo getGrupoArticulo() {
		return grupoArticulo;
	}

	public void setGrupoArticulo(GrupoArticulo grupoArticulo) {
		this.grupoArticulo = grupoArticulo;
	}
	
	public String getAliasUnidad() {
		return aliasUnidad;
	}

	public void setAliasUnidad(String aliasUnidad) {
		this.aliasUnidad = aliasUnidad;
	}

	public InventarioDto toDto() {
		InventarioDto inv = new InventarioDto();
		inv.setAliasUnidad(getAliasUnidad());
		
		if(articulo != null){
			inv.setIdArticulo(articulo.getId());
		}
		
		if(unidadKardex != null){
			inv.setIdUnidadKardex(unidadKardex.getId());
		}
		
		if(unidadAlterna != null){
			inv.setIdUnidadAlterna(unidadAlterna.getId());
		}
		
		if(grupoArticulo != null){
			inv.setIdGrupoArticulo(grupoArticulo.getId());
			inv.setIdClaseArticulo(grupoArticulo.getClaseArticulo().getId());
		}
		
		if(maximosYMinimos != null){
			List<MaximosMinimosDto> mmDto = new ArrayList<MaximosMinimosDto>();
			for(MaximosMinimos mm : maximosYMinimos){
				mmDto.add(mm.toDto());
			}
			inv.setMaximosMinimos(mmDto);
		}
		
		if(unidadesCompra != null){
			List<UnidadDto> unidadesDto = new ArrayList<UnidadDto>();
			for(Unidad u : unidadesCompra){
				unidadesDto.add(u.toDto());
			}
			inv.setUnidadesCompra(unidadesDto);
		}
		
		if(bodegas != null){
			List<BodegaDto> bodegasDto = new ArrayList<BodegaDto>();
			for(Bodega b : bodegas){
				bodegasDto.add(b.toDto());
			}
			inv.setBodegas(bodegasDto);
		}
		
		return inv;
			
	}

}
