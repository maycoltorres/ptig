package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.MarcaDto;

public interface PuntoRepository extends Repository<Punto>{
    public List<Punto> getAll();
	public List<Punto> getByIdCliente(Long clienteId);
	public List<Punto> getByIdClienteInactivo(Long idCliente);
	public List<Punto> getByName(String nombre, Long cliente, Long grupo);
	public List<Punto> getPuntoByIdGrupoCliente(Long idCliente, Long idGrupo, Long idPunto, Long idPunto2);
	public List<Punto> getByName(String nombre);
	public List<Punto> getByArticulo(Long idArticulo);
	public List<Punto> getPuntoByIdpunto(Long idPunto);
	List<Punto> verificarPuntoCliente(String nombre, Long Clienteid);
     
	public String  addpunto(Punto punto);
	public void agregarMarcasAPunto(List<MarcaDto> marcas, Long idpunto);
	public void agregarBodegasAPunto(List<BodegaDto> bodegas, Long idpunto);
	public void actualizarBodegas(List<BodegaDto> bodegas);
}