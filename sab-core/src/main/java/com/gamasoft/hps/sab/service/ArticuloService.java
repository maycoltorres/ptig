package com.gamasoft.hps.sab.service;
/**
 * 
 * @author wospino
 */
import java.util.List;
import java.util.Set;
import java.lang.Long;

import com.gamasoft.hps.sab.dto.ArticuloDto;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.ClaseArticuloDto;
import com.gamasoft.hps.sab.dto.GrupoArticuloDto;
import com.gamasoft.hps.sab.dto.InventarioDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.dto.RecetaDto;
import com.gamasoft.hps.sab.dto.UnidadDto;
import com.gamasoft.hps.sab.dto.UnidadVentaDto;
import com.gamasoft.hps.sab.dto.VentaCanalImpuestoDto;
import com.gamasoft.hps.sab.dto.VentaDto;
import com.gamasoft.hps.sab.exception.RepositoryException;

public interface ArticuloService {

	List<ArticuloDto> getArticulos();	
	List<ArticuloDto> getArticulosInactivos();
	List<ArticuloDto> getArticulosPorClienteYSeleccion(Long idCliente, Boolean seleccion);
	List<ArticuloDto> getArticulosByParams(Long idCliente, Boolean seleccion, Boolean empaque, Boolean inventario, Boolean venta, Boolean receta, 
			Boolean unidadAlternaKardex, Long idUnidadKardex, String mostrar);

	String create(ArticuloDto artic);
	ArticuloDto getById(Long id);
	void update(ArticuloDto art);
	void inactivar (Long id);
	void activar (Long id);
	
	List<BodegaDto> getAvailableBodegasByArticulo(Long idArticulo);
	List<UnidadDto> getUnidadesCompraByArticulo(Long idArticulo);
	String agregarUnidadesCompra(Long idArticulo, List<UnidadDto> unidades) throws RepositoryException;
	
	List<RecetaDto> getRecetasByArticulo(Long idArticulo, String mostrar);
	RecetaDto getRecetaById(Long idReceta);
	String agregarRecetas(Long idArticulo, List<RecetaDto> recetas) throws RepositoryException;
	String actualizarReceta(RecetaDto receta) throws RepositoryException;
	String activarReceta(Long recetaId, Boolean activo) throws RepositoryException;
	
	List<UnidadVentaDto> getUnidadesVentaByArticulo(Long idArticulo);
	String agregarUnidadesVenta(Long idArticulo, List<UnidadVentaDto> unidades) throws RepositoryException;
	
	//CRUD VentaCanalImpuesto
	Set<VentaCanalImpuestoDto> getCanalesImpuesto(Long idArticulo);
	String definirCanalesImpuesto(Long idArticulo, Set<VentaCanalImpuestoDto> canalesImpuestos);
	String adicionarCanalesImpuesto(Long idArticulo, Set<VentaCanalImpuestoDto> canalesImpuestos);
	String eliminarCanalImpuesto(Long idArticulo, Long idCanalImpuesto);
	
	InventarioDto getInventarioByArticulo(Long idArticulo);
	String crearInventario(Long idArticulo, InventarioDto inv);
	String actualizarInventario(Long idArticulo, InventarioDto inv);
	
	VentaDto getVentaByArticulo(Long idArticulo);
	String crearVenta(Long idArticulo, VentaDto inv);
	String actualizarVenta(Long idArticulo, VentaDto inv);
}
