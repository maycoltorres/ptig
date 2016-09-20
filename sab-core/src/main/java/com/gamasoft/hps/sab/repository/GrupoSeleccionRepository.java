package com.gamasoft.hps.sab.repository;

import java.util.List;
import java.util.Set;

import com.gamasoft.hps.sab.domain.GrupoSeleccion;
import com.gamasoft.hps.sab.domain.Seleccion;

public interface GrupoSeleccionRepository extends Repository<GrupoSeleccion> {
	
	public List<GrupoSeleccion> getInactivosByCliente(Long idCliente);
	public List<GrupoSeleccion> getActivosByCliente(Long idCliente);
	public int contarByClienteYNombre(Long idCliente, String nombre);
	public Long getIdByClienteYNombre(Long idCliente, String nombre);
	
	public Set<Seleccion> getSeleccionesByGrupo(Long idGrupo);
	
	//Este método retorna todas las selecciones en las que está incluido el articulo indicado
	public List<GrupoSeleccion> getGruposByArticulo(Long idArticulo);	
	public void createSelecciones(Long idGrupo, Set<Seleccion> selecciones);
	
}
