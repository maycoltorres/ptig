package com.gamasoft.hps.sab.repository;


import java.util.List;

import com.gamasoft.hps.sab.domain.Rol;
import com.gamasoft.hps.sab.domain.RolCliente;
import com.gamasoft.hps.sab.domain.Transaccion;
import com.gamasoft.hps.sab.dto.RolClienteDto;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.dto.TransaccionDto;

public interface RolclienteRepository extends Repository<RolCliente>{

	Long agregarRolCliente(Long clienteDto, Long rolId);

	void agregarTxcliente(Long transac, Long idrolcliente);

	void agregarUserRolCliente(Long id_user, Long id_rolcliente);

	void add(Rol r);

	void agregarTrasaccionRol(Long idRol, List<TransaccionDto> transac);
	
	List<Transaccion> getTransacciones();

	List<Transaccion> getTransaccionesIdRol(Long idrol);

	Long agregarRolCliente0(Long clienteID, Long rolId);

	String elegirRolUser(Long idUser, List<RolClienteDto> rol);

	void adicionaRolesCliente(Long idCliente, List<RolDto> roldto);

	List<RolCliente> getAllactivos(Long idCliente, Long idgrupo);

	void modificarrol(RolCliente rc);

	List<RolCliente> getAllinactivos(Long idCliente);

	String elegirRolesUser(Long idUser, List<RolDto> rol);



	void adicionarolesusuario(Long iduser, List<RolClienteDto> rol);

	void agregarTrasaccionRolUser(Long idRol, List<TransaccionDto> transac);

	List<Transaccion> getTransaccionesIdRolCliente(long idrol);

	List<RolCliente> getRolesByID(long idUser);




}
