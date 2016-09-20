package com.gamasoft.hps.sab.service;
/**
 * 
 * @author wospino
 */
import java.util.List;
import java.lang.Long;

import com.gamasoft.hps.sab.domain.ConfiguracionArticulos;
import com.gamasoft.hps.sab.dto.ArticuloDto;
import com.gamasoft.hps.sab.dto.ConfiguracionArticulosDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;

public interface ConfiguracionArticulosService {

	ConfiguracionArticulosDto getByCliente(Long id);
	
	Long crearConfiguracion(ConfiguracionArticulosDto conf) throws ServiceException;
	
	Long actualizarConfiguracion(ConfiguracionArticulosDto conf) throws RepositoryException,ServiceException;
	
}
