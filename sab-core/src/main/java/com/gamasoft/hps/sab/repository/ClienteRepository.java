package com.gamasoft.hps.sab.repository;

import java.util.List;
import java.util.Set;

import com.gamasoft.hps.sab.domain.Canal;
import com.gamasoft.hps.sab.domain.CanalImpuesto;
import com.gamasoft.hps.sab.domain.Cliente;
import com.gamasoft.hps.sab.domain.Regimen;
import com.gamasoft.hps.sab.domain.Rol;
import com.gamasoft.hps.sab.dto.RolDto;
/**
 *
 * @author vascordoba
 */
public interface ClienteRepository extends Repository<Cliente>{
    public List<Cliente> getAll(Long idCliente);
	public List<Cliente> getAllInactivos(Long idCliente, Long idgrupouser);
    public List<Cliente> getByName(String name);
    public List<Cliente> getBYNIt(String id);
	public List<Cliente> getPorNombre(String nombre);
	public List<Cliente> getClienteByAnyWord(String nombre, Long idclienteuser, Long idgrupo, Long idPunto);
	public void agregarRolaCliente(List<RolDto> clienteDto, long clienteid);
	public List<Rol> getRolesCliente(long clienteId);
	public List<Cliente> getAllByIdGrupo(Long idGrupo);
	public List<Cliente> getbyidAndGrupo(long id, Long idGrupo);
	public List<Cliente> getByIdCliente(long id);
	public Cliente getClienteIdgrupoIdclienteUser(long id, Long idClienteUser,Long idGrupo);
	public String update1(Cliente client);
	public List<Cliente> buscarporIdNit(String id, String nit);
	
	public Set<Regimen> getRegimenesByCliente(long clienteId);
	public void agregarRegimenes(Set<Regimen> regimenes, long clienteId);
	public String eliminarRegimen(long clienteId, long regimenId);
	
	public Set<CanalImpuesto> getCanalImpuestoByCliente(Long idCliente);
	public void definirCanalImpuesto(Long clienteId, Set<CanalImpuesto> canalesImpuestos);
	public void agregarCanalImpuesto(Long clienteId, Set<CanalImpuesto> canalesImpuestos);
	public String eliminarCanalImpuesto(Long idCliente, Long idCanalImpuesto);
	
	public Set<Canal> getCanalesByCliente(Long idCliente);
	public void agregarCanales(Long idCliente, Set<Canal> canales, boolean reemplazar);
	public void eliminarCanal(Long idCliente, Long idCanal);
}
