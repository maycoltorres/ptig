package com.gamasoft.hps.sab.service;
/**
 * 
 * @author wospino
 */
import java.util.List;
import java.util.Set;

import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.EmpaqueDto;
import com.gamasoft.hps.sab.dto.GrupoEmpaqueDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;

public interface GrupoEmpaqueService {

	//Queries
	List<GrupoEmpaqueDto> getGrupoEmpaqueByArticulo(Long idArticulo, Boolean activo);
	List<EmpaqueDto> getEmpaquesByGrupo(Long idGrupo, Boolean activo);
	
	//Transactions
	GrupoEmpaqueDto getById(Long id);
	String create(GrupoEmpaqueDto grupo);
	String actualizar(GrupoEmpaqueDto grupo) throws RepositoryException;
	void inactivar(Long id) throws RepositoryException;
	void activar(Long id) throws RepositoryException;
	
	//Transactions on associated empaques
	String createEmpaque(Long idGrupo, EmpaqueDto empaqueDto);
	String updateEmpaque(EmpaqueDto empaqueDto);
	String activarEmpaque(Long idEmpaque);
	String inactivarEmpaque(Long idEmpaque);
	String eliminarEmpaque(Long idEmpaque);
	
	//Transactions on associated canales
	Set<CanalDto> getCanalesByGrupo(Long idGrupoEmpaque);
	String definirCanales(Long idGrupoEmpaque, Set<CanalDto> canales);
	String adicionarCanales(Long idGrupoEmpaque, Set<CanalDto> canales);
	String eliminarCanal(Long idGrupoEmpaque, Long idCanal);
	
	//Transactions on associated puntos
	Set<PuntoDto> getPuntosByGrupo(Long idGrupoEmpaque);
	String definirPuntos(Long idGrupoEmpaque, Set<PuntoDto> puntos);
	String adicionarPuntos(Long idGrupoEmpaque, Set<PuntoDto> puntos);
	String eliminarPunto(Long idGrupoEmpaque, Long idPunto);
}
