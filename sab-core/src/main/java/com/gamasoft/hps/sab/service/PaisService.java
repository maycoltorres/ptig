package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.domain.Pais;
import com.gamasoft.hps.sab.dto.PaisDto;

public interface PaisService {
	 List<PaisDto> getPaises();
	 Pais getById(long id);
	List<PaisDto> getpaisIdDepartamento(Long idDepartamento);
}
