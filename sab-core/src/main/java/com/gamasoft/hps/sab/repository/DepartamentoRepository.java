package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Departamento;

public interface DepartamentoRepository extends Repository<Departamento> {
	public List<Departamento> getAll();
	public List<Departamento> getdpto(Long id);
	public List<Departamento> getdptoIdmunicipio(Long idMunicipio);
}
