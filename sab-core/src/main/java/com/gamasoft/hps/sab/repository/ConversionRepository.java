package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Conversion;

public interface ConversionRepository extends Repository<Conversion>{

	public List<Conversion> getAllByClienteYArticulo(Long idCliente, Long idArticulo);
	public List<Conversion> getAllByUnidadOrigen(Long idUnidadOrigen);
    public List<Conversion> getAllByUnidadDestino(Long idUnidadDestino);
    public List<Conversion> getByOrigenYDestino(Long idUnidadOrigen, Long idUnidadDestino);
    
}