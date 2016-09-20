package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Pais;
import com.gamasoft.hps.sab.dto.PaisDto;
import com.gamasoft.hps.sab.repository.PaisRepository;
import com.gamasoft.hps.sab.service.PaisService;

@Service
public class LocalPaisService implements PaisService {

	private PaisRepository paisRepository;
	
	public PaisRepository getPaisRepository() {
		return paisRepository;
	}

	@Autowired
	public void setPaisRepository(PaisRepository paisRepository) {
		this.paisRepository = paisRepository;
	}

	@Override
	public List<PaisDto> getPaises() {
		// TODO Auto-generated method stub
		List<Pais> paises = paisRepository.getAll();
		if (paises != null && !paises.isEmpty()) {
			List<PaisDto> paisesDto = new ArrayList<PaisDto>();
			for (Pais c : paises) {
				paisesDto.add(c.toDto());
			}
			return paisesDto;
		}
		return new ArrayList<PaisDto>();
	}

	@Override
	public Pais getById(long id) {
		// TODO Auto-generated method stub
		Pais p = this.paisRepository.getById(id);
		return p;
	}

	@Override
	public List<PaisDto> getpaisIdDepartamento(Long idDepartamento) {
			List<Pais> paises = paisRepository.getPaisIdDepartamento(idDepartamento);
				if (paises != null && !paises.isEmpty()) {
					List<PaisDto> paisesDto = new ArrayList<PaisDto>();
					for (Pais c : paises) {
						paisesDto.add(c.toDto());
					}
					return paisesDto;
				}
				return new ArrayList<PaisDto>();
	}

}
