package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Canal;
import com.gamasoft.hps.sab.domain.Impuesto;
import com.gamasoft.hps.sab.domain.Regimen;
import com.gamasoft.hps.sab.domain.Tarifa;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.ImpuestoDto;
import com.gamasoft.hps.sab.dto.RegimenDto;
import com.gamasoft.hps.sab.dto.TarifaDto;
import com.gamasoft.hps.sab.repository.CanalRepository;
import com.gamasoft.hps.sab.repository.ImpuestoRepository;
import com.gamasoft.hps.sab.repository.RegimenRepository;
import com.gamasoft.hps.sab.repository.TarifaRepository;
import com.gamasoft.hps.sab.service.CommonService;
@Service
public class LocalCommonService implements CommonService{

	@Autowired
	private CanalRepository canalRepository;
	
	@Autowired
	private ImpuestoRepository impuestoRepository;
	
	@Autowired
	private RegimenRepository regimenRepository;
	
	@Autowired
	private TarifaRepository tarifaRepository;
	
	@Override
	public List<CanalDto> getCanales() {
		List<CanalDto> canalesDto = new ArrayList<CanalDto>();
		List <Canal> canales = canalRepository.getAll();
		
		for (Canal c : canales){
			canalesDto.add(c.toDto());
		}
		
		return canalesDto;
	}

	@Override
	public List<ImpuestoDto> getImpuestosByPais(Long idPais) {
		List<ImpuestoDto> impuestosDto = new ArrayList<ImpuestoDto>();
		List <Impuesto> impuestos;
		if(idPais == null){
			impuestos = impuestoRepository.getAll();
		}else{
			impuestos = impuestoRepository.getAllByPais(idPais);
		}
		
		for (Impuesto i : impuestos){
			impuestosDto.add(i.toDto());
		}
		
		return impuestosDto;
	}
	
	@Override
	public List<RegimenDto> getRegimenesByImpuesto(Long idImpuesto) {
		List<RegimenDto> regimenesDto = new ArrayList<RegimenDto>();
		List <Regimen> regimenes;
		if(idImpuesto == null){
			regimenes = regimenRepository.getAll();
		}else{
			regimenes = regimenRepository.getAllByImpuesto(idImpuesto);
		}
		
		for (Regimen r : regimenes){
			regimenesDto.add(r.toDto());
		}
		
		return regimenesDto;
	}

	@Override
	public List<TarifaDto> getTarifasByRegimen(Long idRegimen) {
		List<TarifaDto> tarifasDto = new ArrayList<TarifaDto>();
		List <Tarifa> tarifas;
		
		if(idRegimen != null){
			tarifas = tarifaRepository.getByRegimen(idRegimen);
		}else{
			tarifas = tarifaRepository.getAll();
		}
		
		for(Tarifa t : tarifas){
			tarifasDto.add(t.toDto());
		}
		
		return tarifasDto;
	}
}
