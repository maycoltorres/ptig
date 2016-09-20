package com.gamasoft.hps.sab.service.local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Cliente;
import com.gamasoft.hps.sab.domain.ConfiguracionArticulos;
import com.gamasoft.hps.sab.dto.ConfiguracionArticulosDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.ClienteRepository;
import com.gamasoft.hps.sab.repository.ConfiguracionArticulosRepository;
import com.gamasoft.hps.sab.service.ConfiguracionArticulosService;

@Service
public class LocalConfiguracionArticulosService implements ConfiguracionArticulosService{

	private ConfiguracionArticulosRepository configuracionRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	public void setMunicipioRepository(ConfiguracionArticulosRepository repository) {
		this.configuracionRepository = repository;
	}

	@Override
	public ConfiguracionArticulosDto getByCliente(Long id) {
		ConfiguracionArticulos conf = configuracionRepository.getByCliente(id);
		if (conf != null){
			return conf.toDto();
		}
		return null;
	}

	@Override
	public Long crearConfiguracion(ConfiguracionArticulosDto conf) throws ServiceException {
		if (getByCliente(conf.getIdCliente()) != null){
			throw new ServiceException("Ya existe la configuracion, no puede agregar una nueva");
		}
		
		ConfiguracionArticulos newConf = new ConfiguracionArticulos();
		Cliente cliente = clienteRepository.getById(conf.getIdCliente());
		
		newConf.setCliente(cliente);
		newConf.setUsaCodigoBarras(conf.getUsaCodigoBarras());
		newConf.setUsaUnidadAlterna(conf.getUsaUnidadAlterna());
		newConf.setUsaMaximosMinimos(conf.getUsaMaximosMinimos());
		newConf.setVisto(conf.getVisto());
		newConf.setElementosOcultos(conf.getElementosOcultos());
		
		configuracionRepository.add(newConf);
		
		return newConf.getId();
	}

	@Override
	public Long actualizarConfiguracion(ConfiguracionArticulosDto conf) throws RepositoryException, ServiceException {
		ConfiguracionArticulos c = configuracionRepository.getByCliente(conf.getIdCliente());
		
		if (c == null){
			throw new ServiceException("No existe la configuracion, no se puede actualizar");
		}
		
		
		c.setUsaCodigoBarras(conf.getUsaCodigoBarras());
		c.setUsaUnidadAlterna(conf.getUsaUnidadAlterna());
		c.setUsaMaximosMinimos(conf.getUsaMaximosMinimos());
		c.setVisto(conf.getVisto());
		c.setElementosOcultos(conf.getElementosOcultos());
		
		configuracionRepository.update(c);
		
		return c.getId();
	}

}
