package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Municipio;
import com.gamasoft.hps.sab.dto.MunicipioDto;
import com.gamasoft.hps.sab.repository.MunicipioRepository;
import com.gamasoft.hps.sab.service.MunicipioService;

@Service
public class LocalMunicipioService implements MunicipioService{

	private MunicipioRepository municipioRepository;
	
	@Autowired
	public void setMunicipioRepository(MunicipioRepository municipioRepository) {
		this.municipioRepository = municipioRepository;
	}

	@Override
	public List<MunicipioDto> getMunicipios() {
		// TODO Auto-generated method stub
		List<Municipio> Municipios = municipioRepository.getAll();
		if (Municipios != null && !Municipios.isEmpty()) {
			List<MunicipioDto> MunicipiosDto = new ArrayList<MunicipioDto>();
			for (Municipio c : Municipios) {
				MunicipiosDto.add(c.toDto());
			}
			return MunicipiosDto;
		}
		return new ArrayList<MunicipioDto>();

	}

	@Override
	public Municipio getById(long id) {
		// TODO Auto-generated method stub
		return this.municipioRepository.getById(id);
	}

	@Override
	public List<MunicipioDto> getByIdDepto(long dptoId) {
		// TODO Auto-generated method stub
		List<Municipio> dto = municipioRepository.getByDpto(dptoId);
		if(dto !=null && !dto.isEmpty()){
			List<MunicipioDto> municipioDto = new ArrayList<MunicipioDto>();
			for(Municipio c : dto){
				municipioDto.add(c.toDto());
			}
			return municipioDto;
		}
		
		
		return new ArrayList<MunicipioDto>();
	}

}
