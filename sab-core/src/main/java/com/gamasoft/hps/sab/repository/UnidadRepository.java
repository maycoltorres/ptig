package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.dto.MarcaDto;

public interface UnidadRepository extends Repository<Unidad>{
    public List<Unidad> getAll();

	public List<Unidad> getUnidadesByCriteria(Long idCliente, String tipo, String mostrar);

}