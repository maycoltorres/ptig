package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Impuesto;

public interface ImpuestoRepository extends Repository<Impuesto> {

	public List<Impuesto> getAll();
	public List<Impuesto> getAllByPais(Long idPais);
	
}
