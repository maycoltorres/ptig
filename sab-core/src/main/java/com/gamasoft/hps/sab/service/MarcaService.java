package com.gamasoft.hps.sab.service;

import java.util.List;

import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;


public interface MarcaService {
	List<MarcaDto> getMarcas();
	String add(MarcaDto marcaDto);
	MarcaDto getById(long id);
	String update(MarcaDto marca, long puntoId);

	String inactiveMarca(long marcaId);
	String adicionaPunto(long marcaId, long puntoId);
	String activateMarca(long marcaId);
	List<MarcaDto> getMarcasInactivas();
	void eliminarPuntos(List<PuntoDto> puntoDto, long marcaId);
	void quitarPunto(long marcaId, long puntoId);
	List<MarcaDto> getMarcasProNombre(String nombre);
	String adicionaMarca(List<MarcaDto> marcaDto, Long idpunto);
}
