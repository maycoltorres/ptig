package com.gamasoft.hps.sab.repository;

import com.gamasoft.hps.sab.domain.ConfiguracionCliente;
/**
 *
 * @author vascordoba
 */
public interface ConfiguracionClienteRepository extends Repository<ConfiguracionCliente>{
    public ConfiguracionCliente getByCliente(Long idCliente);
}
