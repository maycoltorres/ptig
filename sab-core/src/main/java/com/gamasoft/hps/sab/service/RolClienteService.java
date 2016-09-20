package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.dto.RolClienteDto;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.dto.TransaccionDto;



public interface RolClienteService {

	void adicionaRolCliente(Long idCliente, Long clienteId, Long Idgrupo);

	List<RolClienteDto> getAll(Long idCliente, Long idGrupo);

	void adicionaTxCliente(Long transac, Long idrolcliente);

	void adicionarUserRolcliente(Long id_user, Long id_rolcliente);

	String crearRol(RolClienteDto rolClienteDto);

	String adicionaTrasaccionRol(Long idRol, List<TransaccionDto> transac);

	List<TransaccionDto> getTransacciones();

	List<TransaccionDto> getTransaccionesDelRol(long idrol);

	String elegirRolUser(Long idUser, List<RolClienteDto> rol);

	String adicionaRolesCliente(Long idCliente, List<RolDto> roldto);

	String modificarrol(Long idRolcliente, RolClienteDto rolclientedto);

	RolClienteDto getRolClienteXid(long idrol);

	List<RolClienteDto> getAllinactivos(Long idUserRequestMapping, Long idUserRequestMapping2);

	String elegirRolesUser(Long idUser, List<RolDto> rol);

	String adicionarolesusuario(long idUser, List<RolClienteDto> rol);

	String inactivarRolCliente(long idrol);

	String activarRolCliente(long idrol);

	String adicionaTrasaccionRolUser(Long idRol, List<TransaccionDto> transac);

	List<TransaccionDto> getTransaccionesDelRolCliente(long idrol);

	List<RolClienteDto> getRolesUsuario(long idUser);
}
