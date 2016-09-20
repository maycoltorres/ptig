package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Conversion;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.dto.ConversionDto;
import com.gamasoft.hps.sab.dto.UnidadDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.ArticuloRepository;
import com.gamasoft.hps.sab.repository.ConversionRepository;
import com.gamasoft.hps.sab.repository.UnidadRepository;
import com.gamasoft.hps.sab.service.UnidadService;

@Service
public class LocalUnidadService implements UnidadService {

	@Autowired
	private UnidadRepository unidadRepository;
	
	@Autowired
	private ConversionRepository conversionRepository;
	
	@Autowired
	private ArticuloRepository articuloRepository;
	
	@Override
	public List<UnidadDto> getAll() {
		List<Unidad> unidades = unidadRepository.getAll();
		List<UnidadDto> unidadesDto = new ArrayList<UnidadDto>();
		
		if (unidades != null && !unidades.isEmpty()) {
			for (Unidad u : unidades) {
				unidadesDto.add(u.toDto());
			}
		}
		
		return unidadesDto;
	}

	@Override
	public List<UnidadDto> getUnidadesByCriteria(Long idCliente, String tipo, String mostrar) {
		List<Unidad> unidades = unidadRepository.getUnidadesByCriteria(idCliente, tipo, mostrar);
		List<UnidadDto> unidadesDto = new ArrayList<UnidadDto>();
		
		if (unidades != null && !unidades.isEmpty()) {
			for (Unidad u : unidades) {
				unidadesDto.add(u.toDto());
			}
		}
		
		return unidadesDto;
	}

	@Override
	public Long crearUnidad(UnidadDto unidadDto) throws ServiceException {
		Unidad u = new Unidad();
		u.setEstandar(false);
		u.setIdCliente(unidadDto.getIdCliente());
		u.setNombre(unidadDto.getNombre());
		unidadRepository.add(u);
		
		if (unidadDto.getConversiones() != null){
			for(ConversionDto convDto : unidadDto.getConversiones()){
				Conversion c = new Conversion();
				c.setUnidadOrigen(u);
				c.setUnidadDestino(unidadRepository.getById(convDto.getIdUnidadDestino()));
				c.setFactor(convDto.getFactor());
				if(convDto.getIdArticulo() != null){
					c.setArticulo(articuloRepository.getById(convDto.getIdArticulo()));
				}
				
				conversionRepository.add(c);
			}
		}
		
		return u.getId();
	}

	@Override
	public List<ConversionDto> getAllByUnidadOrigen(Long idUnidadOrigen) {
		List<ConversionDto> conversionesDto = new ArrayList<ConversionDto>();
		List<Conversion> conversiones = conversionRepository.getAllByUnidadOrigen(idUnidadOrigen);
		for(Conversion c : conversiones){
			conversionesDto.add(c.toDto());
		}
		return conversionesDto;
	}

	@Override
	public List<ConversionDto> getAllByUnidadDestino(Long idUnidadDestino) {
		List<ConversionDto> conversionesDto = new ArrayList<ConversionDto>();
		List<Conversion> conversiones = conversionRepository.getAllByUnidadDestino(idUnidadDestino);
		for(Conversion c : conversiones){
			conversionesDto.add(c.toDto());
		}
		return conversionesDto;
	}

	@Override
	public List<ConversionDto> getByOrigenYDestino(Long idUnidadOrigen,
			Long idUnidadDestino) {
		List<ConversionDto> conversionesDto = new ArrayList<ConversionDto>();
		List<Conversion> conversiones = conversionRepository.getByOrigenYDestino(idUnidadOrigen, idUnidadDestino);
		for(Conversion c : conversiones){
			conversionesDto.add(c.toDto());
		}
		return conversionesDto;
	}

	@Override
	public List<ConversionDto> getAllByClienteYArticulo(Long idCliente, Long idArticulo) {
		List<ConversionDto> conversionesDto = new ArrayList<ConversionDto>();
		List<Conversion> conversiones = conversionRepository.getAllByClienteYArticulo(idCliente, idArticulo);
		for(Conversion c : conversiones){
			conversionesDto.add(c.toDto());
		}
		return conversionesDto;
	}

	@Override
	public Long adicionarConversion(Long idUnidad,
			ConversionDto convDto) throws ServiceException {
		Unidad u = unidadRepository.getById(idUnidad);
		
		Conversion c = new Conversion();
		c.setUnidadOrigen(u);
		c.setUnidadDestino(unidadRepository.getById(convDto.getIdUnidadDestino()));
		c.setFactor(convDto.getFactor());
		if(convDto.getIdArticulo() != null){
			c.setArticulo(articuloRepository.getById(convDto.getIdArticulo()));
		}
			
		conversionRepository.add(c);
		
		return c.getId();
	}

	@Override
	public String actualizarConversion(Long idUnidad,
			ConversionDto convDto) throws ServiceException {
		try {
			Conversion conv = conversionRepository.getById(convDto.getId());
			conv.setFactor(convDto.getFactor());
			conversionRepository.update(conv);
		} catch (RepositoryException e) {
			return "Error";
		}
		
		return "OK";
	}

}
