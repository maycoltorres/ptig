/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service;

import java.util.List;
import java.util.Set;

import com.gamasoft.hps.sab.domain.Cliente;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.CanalImpuestoDto;
import com.gamasoft.hps.sab.dto.ClaseArticuloDto;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.ConfiguracionClienteDto;
import com.gamasoft.hps.sab.dto.GrupoArticuloDto;
import com.gamasoft.hps.sab.dto.ListaPreciosDto;
import com.gamasoft.hps.sab.dto.RegimenDto;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.exception.ServiceException;

/**
 *
 * @author vascordoba
 */
public interface ClienteService {
    List<ClienteDto> getClients(Long idCliente, Long idGrupo) ;
    Cliente getbyid(Long id);
    String add(ClienteDto clientDto) throws ServiceException;
    String delete(Long clientId);
    String update(ClienteDto clienteDto, long idCliente, Long idClienteuser, Long idGrupo);
    ClienteDto getById(long idCliente, Long idClienteUser, Long idGrupo);
    ClienteDto getByName(String name);
    String GetByNit(String nit);
	String inactiveCliente(long clienteId, Long long1);
	String activateCliente(long clienteId);
	List<ClienteDto> getClientsInactivos(Long idClienteUser, Long IdGrupoUser);
	List<ClienteDto> getClientesProNombre(String nombre);
	List<ClienteDto> getClienteNombreAnyWord(String nombre, Long long1, Long long2, Long long3);
	
	ConfiguracionClienteDto getConfiguracionCliente(Long idCliente);
	String definirConfiguracionCliente(Long idCliente, ConfiguracionClienteDto confClienteDto);
	
	List<RolDto> getRolesCliente(long clienteId);
	String adicionaRol(List<RolDto> rolDto, long clienteid);
	
	List<RegimenDto> getRegimenes(Long idCliente);
    String adicionaRegimen(List<RegimenDto> regimenes, long idCliente);
	String eliminaRegimen(Long idCliente, Long idRegimen);
	
	List<ListaPreciosDto> getListasPreciosPorCliente(Long idCliente, Boolean activo);
	String adicionarListaPrecios(Long idCliente, ListaPreciosDto lista);
	String actualizarListaPrecios(ListaPreciosDto lista);
	String eliminarListaPrecios(ListaPreciosDto lista);
	void inactivarListaPrecios(Long id);
	void activarListaPrecios(Long id);
	
	List<ClaseArticuloDto> getClaseArticuloByCliente(Long idCliente, Boolean venta, Boolean inventario, Boolean activo);
	ClaseArticuloDto getClaseArticuloById(Long idClase);
	List<GrupoArticuloDto> getGrupoArticuloByClase(Long idClase);
	String agregarClaseArticulo(ClaseArticuloDto claseDto);
	String actualizarClaseArticulo(ClaseArticuloDto claseDto);
	String activarClaseArticulo(Long idClaseArticulo, Boolean activo);
	
	List<CanalImpuestoDto> getCanalImpuesto(Long idCliente);
    String adicionarCanalImpuesto(Long idCliente, CanalImpuestoDto canalImpuesto);
    String actualizarCanalImpuesto(CanalImpuestoDto canalImpuesto);
	String eliminarCanalImpuesto(Long idCanalImpuesto);
	
	Set<CanalDto> getCanalesPorCliente(Long idCliente);
	String definirCanales(Long idCliente, List<CanalDto> canalesDto);
	String adicionarCanales(Long idCliente, List<CanalDto> canalesDto);
	String eliminarCanal(Long idCliente, Long idCanal);
}
