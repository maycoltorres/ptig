package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.ImpuestoDto;
import com.gamasoft.hps.sab.dto.RegimenDto;
import com.gamasoft.hps.sab.dto.TarifaDto;

public interface CommonService {
	List<CanalDto> getCanales();
	List<ImpuestoDto> getImpuestosByPais(Long idPais);
	List<RegimenDto> getRegimenesByImpuesto(Long idImpuesto);
	List<TarifaDto> getTarifasByRegimen(Long idRegimen);
}