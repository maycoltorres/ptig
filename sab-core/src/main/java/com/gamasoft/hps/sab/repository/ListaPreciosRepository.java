package com.gamasoft.hps.sab.repository;


import java.util.List;

import com.gamasoft.hps.sab.domain.ListaPrecios;

public interface ListaPreciosRepository extends Repository<ListaPrecios>{
	
	List<ListaPrecios> getByCliente(Long idCliente, Boolean activo);
	ListaPrecios getByClienteYNombre(Long idCliente, String nombre);
    
}