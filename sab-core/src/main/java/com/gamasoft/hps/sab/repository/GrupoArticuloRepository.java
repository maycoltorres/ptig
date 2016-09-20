package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.GrupoArticulo;

public interface GrupoArticuloRepository extends Repository<GrupoArticulo> {
	
	public List<GrupoArticulo> getAllByClase(Long idClase);
	public List<GrupoArticulo> getByClaseYNombre(Long idClase, String nombre);
	
}
