package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.repository.ArticuloRepository;
import com.gamasoft.hps.sab.repository.BodegaRepository;
import com.gamasoft.hps.sab.service.BodegaService;

@Service
public class LocalBodegaService implements  BodegaService {
	
	@Autowired
	private BodegaRepository bodegaRepository; 
	

	@Override
	public List<BodegaDto> getBodegasByArticulo(Long idArticulo, String mostrar) {
		List<Bodega> bodegas = bodegaRepository.getByArticulo(idArticulo, mostrar);
		List<BodegaDto> bodegasDto = new ArrayList<BodegaDto>();
		for(Bodega b : bodegas){
			bodegasDto.add(b.toDto());
		}
		
		return bodegasDto;
	}


	@Override
	public List<BodegaDto> getBodegasByCliente(Long idCliente, String mostrar) {
		List<Bodega> bodegas = bodegaRepository.getByCliente(idCliente, mostrar);
		List<BodegaDto> bodegasDto = new ArrayList<BodegaDto>();
		for(Bodega b : bodegas){
			bodegasDto.add(b.toDto());
		}
		
		return bodegasDto;
	}

}
