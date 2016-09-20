package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Departamento;
import com.gamasoft.hps.sab.dto.DepartamentoDto;
import com.gamasoft.hps.sab.repository.DepartamentoRepository;
import com.gamasoft.hps.sab.service.DepartamentoService;

@Service
public class LocalDepartamentoService implements DepartamentoService {

	private DepartamentoRepository departamentoRepository; 
	
	

	@Autowired
	public void setDepartamentoRepository(DepartamentoRepository departamentoRepository) {
		this.departamentoRepository = departamentoRepository;
	}

	@Override
	public List<DepartamentoDto> getDepartamentos() {
		// TODO Auto-generated method stub
		List<Departamento> dpto = departamentoRepository.getAll();
		if (dpto != null && !dpto.isEmpty()) {
			List<DepartamentoDto> departamentosDto = new ArrayList<DepartamentoDto>();
			for (Departamento c : dpto) {
				departamentosDto.add(c.toDto());
			}
			return departamentosDto;
		}
		return new ArrayList<DepartamentoDto>();
	}

	@Override
	public Departamento getById(long id) {
		// TODO Auto-generated method stub
		Departamento d = this.departamentoRepository.getById(id);
		return d;
	}

	@Override
	public List<DepartamentoDto> getByidPais(Long id) {
		// TODO Auto-generated method stub
		List<Departamento> dto = departamentoRepository.getdpto(id);
		if(dto !=null && !dto.isEmpty()){
			List<DepartamentoDto> departamentoDto = new ArrayList<DepartamentoDto>();
			for(Departamento c : dto){
				departamentoDto.add(c.toDto());
			}
			return departamentoDto;
		}
		
		
		return new ArrayList<DepartamentoDto>();
	}

	@Override
	public List<DepartamentoDto> getdepartamentoidMunicipio(Long idMunicipio) {
		// TODO Auto-generated method stub
		List<Departamento> dto = departamentoRepository.getdptoIdmunicipio(idMunicipio);
		if(dto !=null && !dto.isEmpty()){
			List<DepartamentoDto> departamentoDto = new ArrayList<DepartamentoDto>();
			for(Departamento c : dto){
				departamentoDto.add(c.toDto());
			}
			return departamentoDto;
		}
		return new ArrayList<DepartamentoDto>();
	}

}
