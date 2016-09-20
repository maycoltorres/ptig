package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Regimen;

public interface RegimenRepository extends Repository<Regimen> {

	public List<Regimen> getAll();
	public List<Regimen> getAllByImpuesto(Long idImpuesto);

}
