package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Empaque;

public interface EmpaqueRepository extends Repository<Empaque> {
	List<Empaque> getByGrupo(Long idGrupoEmpaque, Boolean activo);
	
}
