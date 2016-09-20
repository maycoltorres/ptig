package com.gamasoft.hps.sab.repository;


import java.util.List;

import com.gamasoft.hps.sab.domain.UnidadVenta;

public interface UnidadVentaRepository extends Repository<UnidadVenta>{
 
	public List<UnidadVenta> getByArticulo(Long idArticulo);
}