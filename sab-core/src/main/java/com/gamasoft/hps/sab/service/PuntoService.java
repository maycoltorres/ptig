/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;

/**
 *
 * @author vascordoba
 */
public interface PuntoService {	//Estas funciones son implementadas en la clase HibernatePuntoRepository del paquete com.gamsoft.hps.sab.repository.hibernate
    List<PuntoDto> getPuntos(Long idCliente, Long idClienteUser, Long idGrupo, Long idPunto);	//devuelve en una lista todos los puntos que existen en la base de datos.
    List<PuntoDto> getPuntPorNombre(String nombre, Long Cliente, Long Usuario, Long iduser);
    List<PuntoDto> getPuntosInactivos(Long idCliente, Long IdClienteUser);
    List<PuntoDto> getPuntosByArticulo(Long idArticulo);
	
    String add(PuntoDto puntoDto, Long long1);		   //Adiciona un nuevo punto.
    void delete(Long PuntoId);			  //recibe el id del punto a eliminar.
    String update(PuntoDto punto, long id); //recibe el Dto para actualizar el punto y el id del punto que se debera actualizar.
	PuntoDto getById(long id);
	
	String inactivePunto(long puntoId);
	String activatePunto(long puntoId);
	
	List<MarcaDto> getMarcasPorPunto(Long idPunto);
	String adicionaMarcas(List<MarcaDto> marcas, Long idpunto);
	String eliminarMarca(Long idMarca, Long idPunto);
	
	List<BodegaDto> getBodegasPorPunto(Long idPunto);
	String adicionaBodegas(List<BodegaDto> bodegas, Long idpunto) throws ServiceException;
	String actualizarBodega(BodegaDto bodegaDto);
	String activarBodega(Long idBodega) throws RepositoryException;
	String desactivarBodega(Long idBodega) throws RepositoryException;
	
}
