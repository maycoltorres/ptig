package com.gamasoft.hps.sab.repository;

import com.gamasoft.hps.sab.domain.Articulo;
import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.CanalImpuesto;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.domain.VentaCanalImpuesto;
import com.gamasoft.hps.sab.dto.ArticuloDto;

import java.util.List;
import java.util.Set;

public interface ArticuloRepository extends Repository<Articulo> {
	
	public List<Articulo> getInactivos();
	
	public List<Articulo> getByClienteYSeleccion(Long idCliente, Boolean seleccion);
	public Articulo getByClienteYNombre(Long idCliente, String nombre);
	List<Articulo> getArticulosByParams(Long idCliente, Boolean seleccion, Boolean empaque, Boolean inventario, Boolean venta, Boolean receta, 
			Boolean unidadAlternaKardex, Long idUnidadKardex, String mostrar);
	
	public List<Bodega> getByArticulo(Long idArticulo, Boolean showAll);
	public Set<Unidad> getUnidadesCompraByArticulo(Long idArticulo);
	
	public Set<VentaCanalImpuesto> getCanalImpuestoByArticulo(Long idArticulo);
	public void definirCanalImpuesto(Long idArticulo, Set<VentaCanalImpuesto> canalesImpuestos);
	public void agregarCanalImpuesto(Long idArticulo, Set<VentaCanalImpuesto> canalesImpuestos);
	public String eliminarCanalImpuesto(Long idArticulo, Long idCanalImpuesto);

}
