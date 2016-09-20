package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Grupo;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.GrupoDto;
import com.gamasoft.hps.sab.exception.ServiceException;

public interface GrupoRepository extends Repository<Grupo> {

	public List<Grupo> getAll();
	public void agregarClienteAGrupo(List<ClienteDto> clienteDto, Long idGrupo);
	public GrupoDto getGrupoCliente(long grupoId);
	public String agregarClienteAGrupo(long grupoId, long clienteId) throws ServiceException;
	public void quitarClienteAGrupo(long grupoId, long clienteId);
	public List<Grupo> getAllInactivos();
	public void eliminarClientesDeGrupo(List<ClienteDto> clienteDto,long grupoId);
	public  List<Grupo> getPorNombre(String nombre);
	public List<Grupo> getByname(String nombre);
	public List<Grupo> getByname(String nombre, Long cliente, Long grupo);
	public List<Grupo> getGrupoIdCliente(long idCliente);
	
}
