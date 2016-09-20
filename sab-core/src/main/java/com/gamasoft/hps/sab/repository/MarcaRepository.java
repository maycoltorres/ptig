package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Marca;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;

public interface MarcaRepository extends Repository<Marca> {

	public List<Marca> getAll();
	public void agregarMarcaPunto(List<MarcaDto> puntoDto, Long idMarca);
	public List<MarcaDto> getMarcasPorPunto(long idPunto);
	public void quitarPuntoAMarca(long marcaId, long puntoId);
	public List<Marca> getAllInactivos();
	public void eliminarPuntosDeMarca(List<PuntoDto> puntoDto,long marcaId);
	public List<Marca> getPorNombre(String nombre);
	public String agregarPuntoAMarca(long marcaId, long puntoId);

}
