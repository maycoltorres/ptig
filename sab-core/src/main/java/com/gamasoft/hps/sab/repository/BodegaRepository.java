package com.gamasoft.hps.sab.repository;


import java.util.List;

import com.gamasoft.hps.sab.domain.Bodega;

public interface BodegaRepository extends Repository<Bodega>{
 
	public List<Bodega> getByPuntoAndNombre(Long idPunto, String nombre);
	public List<Bodega> getByArticulo(Long idArticulo, String mostrar);
	public List<Bodega> getByCliente(Long idCliente, String mostrar);
}
