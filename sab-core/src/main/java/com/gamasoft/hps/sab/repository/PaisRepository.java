package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Pais;

public interface PaisRepository extends Repository<Pais>{
    public List<Pais> getAll();

	public List<Pais> getPaisIdDepartamento(Long idDepartamento);
       
}