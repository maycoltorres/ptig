package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.domain.Departamento;
import com.gamasoft.hps.sab.dto.DepartamentoDto;

public interface DepartamentoService {

	List<DepartamentoDto> getDepartamentos();
	Departamento getById(long id);
	List<DepartamentoDto> getByidPais(Long id);
	List<DepartamentoDto> getdepartamentoidMunicipio(Long idMunicipio);
}
