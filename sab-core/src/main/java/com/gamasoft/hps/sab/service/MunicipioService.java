package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.domain.Municipio;
import com.gamasoft.hps.sab.dto.MunicipioDto;

public interface MunicipioService {
	List<MunicipioDto> getMunicipios();
	Municipio getById(long id);
	List<MunicipioDto> getByIdDepto(long dptoId);
}
