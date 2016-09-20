/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.dto.BodegaDto;

/**
 *
 * @author vascordoba
 */
public interface BodegaService {	
    List<BodegaDto> getBodegasByArticulo(Long idArticulo, String mostrar);	
    List<BodegaDto> getBodegasByCliente(Long idCliente, String mostrar);
}
