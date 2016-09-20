package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Impuesto;
import com.gamasoft.hps.sab.domain.Tarifa;

public interface TarifaRepository extends Repository<Tarifa> {

	public List<Tarifa> getByRegimen(Long idRegimen);

}
