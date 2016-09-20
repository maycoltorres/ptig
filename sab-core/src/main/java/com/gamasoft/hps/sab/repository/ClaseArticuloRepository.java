package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.ClaseArticulo;

public interface ClaseArticuloRepository extends Repository<ClaseArticulo> {

	public List<ClaseArticulo> getAllByCliente(Long idCliente, Boolean venta, Boolean inventario, Boolean activo);
	public ClaseArticulo getByClienteYNombre(Long idCliente, String nombre);

}
