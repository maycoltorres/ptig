package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.domain.Conversion;
import com.gamasoft.hps.sab.domain.Pais;
import com.gamasoft.hps.sab.dto.ConversionDto;
import com.gamasoft.hps.sab.dto.PaisDto;
import com.gamasoft.hps.sab.dto.UnidadDto;
import com.gamasoft.hps.sab.exception.ServiceException;

public interface UnidadService {
	 List<UnidadDto> getAll();
	 List<UnidadDto> getUnidadesByCriteria(Long idCliente, String tipo, String mostrar);
	 Long crearUnidad(UnidadDto unidadDto) throws ServiceException;
	 Long adicionarConversion(Long idUnidad, ConversionDto conversionDto) throws ServiceException;
	 String actualizarConversion(Long idUnidad, ConversionDto conversionDto) throws ServiceException;
	 
	 public List<ConversionDto> getAllByClienteYArticulo(Long idCliente, Long idArticulo);
	 public List<ConversionDto> getAllByUnidadOrigen(Long idUnidadOrigen);
	 public List<ConversionDto> getAllByUnidadDestino(Long idUnidadDestino);
	 public List<ConversionDto> getByOrigenYDestino(Long idUnidadOrigen, Long idUnidadDestino);
	    
}
