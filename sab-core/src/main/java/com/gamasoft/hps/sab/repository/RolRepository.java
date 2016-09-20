package com.gamasoft.hps.sab.repository;


import java.util.List;

import com.gamasoft.hps.sab.domain.Rol;
import com.gamasoft.hps.sab.dto.TransaccionDto;

public interface RolRepository extends Repository<Rol> {

	List<Rol> getInactived();

	List<Rol> getByname(String nombre, Long cliente, Long grupo);

	List<Rol> getAllbyIdcliente(long rolId);

	List<Rol> getByname(String nombre);

	void agregarTxRol(Long transac, List<TransaccionDto> transac2);

	List<Rol> getById(Long rolId, Long idgrupo);

	





		
}
