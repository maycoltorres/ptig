package com.gamasoft.hps.sab.service;
/**
 * 
 * @author wospino
 */
import java.util.List;


import java.util.Set;

import com.gamasoft.hps.sab.dto.GrupoSeleccionDto;
import com.gamasoft.hps.sab.dto.SeleccionDto;
import com.gamasoft.hps.sab.exception.RepositoryException;

public interface GrupoSeleccionService {

	//Queries
	List<GrupoSeleccionDto> getGrupoSeleccionByCliente(Long idCliente, Boolean activo);
	List<GrupoSeleccionDto> getGrupoSeleccionByArticulo(Long idArticulo);
	List<SeleccionDto> getSeleccionesByGrupo(Long idGrupo);
	
	//Transactions
	GrupoSeleccionDto getById(Long id);
	String create(GrupoSeleccionDto grupo);
	void update(GrupoSeleccionDto grupo) throws RepositoryException;
	void inactivar(Long id) throws RepositoryException;
	void activar(Long id) throws RepositoryException;
	
	//Transactions on associated selecciones
	String createSeleccion(Long idGrupo, Set<SeleccionDto> seleccionesDto);
	/*void update(GrupoSeleccionDto grupo) throws RepositoryException;
	void inactivar(Long id) throws RepositoryException;
	void activar(Long id) throws RepositoryException;*/
}
