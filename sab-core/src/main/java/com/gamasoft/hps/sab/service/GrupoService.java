package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.GrupoDto;

public interface GrupoService {
	 List<GrupoDto> getGrupos();
	 String add(GrupoDto grupoDto);
	 GrupoDto getById(long id);
	String update(GrupoDto grupo, long clienteId);
	String adicionaCliente(List<ClienteDto> clienteDto, Long idGrupo);
	GrupoDto getGrupoCliente(long grupoId);
	String inactiveGrupo(long grupoId);
	String adicionaCliente(long grupoId, long clienteId);
	String activateGrupo(long grupoId);
	void quitarCliente(long grupoId, long clienteId);
	List<GrupoDto> getGruposinactivos();
	void eliminarClientes(List<ClienteDto> clienteDto, long grupoId);
	List<GrupoDto> getGrupoPorNombre(String nombre, Long long1, Long long2);
	List<GrupoDto> getGrupoIdCliente(long idCliente);
	
}