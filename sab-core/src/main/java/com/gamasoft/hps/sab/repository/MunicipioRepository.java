package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Municipio;

public interface MunicipioRepository extends Repository<Municipio> {
	public List<Municipio> getAll();

	public List<Municipio> getByDpto(long dptoId);
}
