package com.gamasoft.hps.sab.domain;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author wospino
 */
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.CanalImpuestoDto;
import com.gamasoft.hps.sab.dto.UnidadVentaDto;
import com.gamasoft.hps.sab.dto.VentaCanalImpuestoDto;
import com.gamasoft.hps.sab.dto.VentaDto;
import com.gamasoft.hps.sab.dto.VentaGrupoSeleccionDto;

@Entity
@Table(name = "venta")
public class Venta extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;
	
	@OneToOne
    @JoinColumn(name = "id_grupoarticulo")
    private GrupoArticulo grupoArticulo;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<UnidadVenta> unidadesVenta;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<VentaCanalImpuesto> canalesImpuesto;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<VentaGrupoSeleccion> gruposSeleccion;
	
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public GrupoArticulo getGrupoArticulo() {
		return grupoArticulo;
	}
	
	public Set<VentaCanalImpuesto> getCanalesImpuesto() {
		return canalesImpuesto;
	}

	public void setCanalesImpuesto(Set<VentaCanalImpuesto> canalesImpuesto) {
		this.canalesImpuesto = canalesImpuesto;
	}

	public void setGrupoArticulo(GrupoArticulo grupoArticulo) {
		this.grupoArticulo = grupoArticulo;
	}
	
	public Set<VentaGrupoSeleccion> getGruposSeleccion() {
		return gruposSeleccion;
	}

	public void setGruposSeleccion(Set<VentaGrupoSeleccion> gruposSeleccion) {
		this.gruposSeleccion = gruposSeleccion;
	}
	
	public Set<UnidadVenta> getUnidadesVenta() {
		return unidadesVenta;
	}

	public void setUnidadesVenta(Set<UnidadVenta> unidadesVenta) {
		this.unidadesVenta = unidadesVenta;
	}

	public VentaDto toDto() {
		VentaDto venta = new VentaDto();
		
		if(articulo != null){
			venta.setIdArticulo(articulo.getId());
		}
		
		if(grupoArticulo!= null){
			venta.setIdGrupoArticulo(grupoArticulo.getId());
			venta.setIdClaseArticulo(grupoArticulo.getClaseArticulo().getId());
		}
		
		if(gruposSeleccion!= null){
			List<VentaGrupoSeleccionDto> grupos = new ArrayList<VentaGrupoSeleccionDto>();
			
			for(VentaGrupoSeleccion ventaGrupoS : gruposSeleccion){
				grupos.add(ventaGrupoS.toDto());
			}
			
			venta.setGruposSeleccion(grupos);
		}
		
		if(canalesImpuesto!= null){
			List<VentaCanalImpuestoDto> canales = new ArrayList<VentaCanalImpuestoDto>();
			
			for(VentaCanalImpuesto canalImpuesto : canalesImpuesto){
				canales.add(canalImpuesto.toDto());
			}
			
			venta.setCanalesImpuestos(canales);
		}
		
		if(unidadesVenta!= null){
			List<UnidadVentaDto> unidades = new ArrayList<UnidadVentaDto>();
			
			for(UnidadVenta unidadVenta : unidadesVenta){
				unidades.add(unidadVenta.toDto());
			}
			
			venta.setUnidadesVenta(unidades);
		}
		
		return venta;		
	}
	
}
