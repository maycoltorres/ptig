package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.ConfiguracionArticulos;
import com.gamasoft.hps.sab.domain.Pais;

public interface ConfiguracionArticulosRepository extends Repository<ConfiguracionArticulos>{
    public ConfiguracionArticulos getByCliente(Long id);

}