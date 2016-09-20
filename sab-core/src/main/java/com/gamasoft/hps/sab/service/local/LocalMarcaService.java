package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Marca;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.MarcaRepository;
import com.gamasoft.hps.sab.service.MarcaService;
@Service
public class LocalMarcaService implements MarcaService{

	private MarcaRepository marcaRepository;
	
	
	@Autowired
	public void setMarcaRepository(MarcaRepository marcarepository) {
		this.marcaRepository = marcarepository;
	}


	@Override
	public List<MarcaDto> getMarcas() {
		// TODO Auto-generated method stub
		List<Marca> marcas = marcaRepository.getAll();
		if(marcas !=null && !marcas.isEmpty()){
			List<MarcaDto> dto = new ArrayList<MarcaDto>();
			for(Marca m : marcas){
				dto.add(m.toDtoLess());
			}
			return dto;
		}
		return new ArrayList<MarcaDto>();
	}


	@Override
	public String add(MarcaDto marcaDto) {
		// TODO Auto-generated method stub

		Marca m = new Marca(marcaDto);
		try{
		this.marcaRepository.add(m);
		return "Marca Creada";
	}catch(Exception e){
		return "Marca ya existe";
	}
		}


	@Override
	public MarcaDto getById(long id) {
		// TODO Auto-generated method stub
		MarcaDto marca = new MarcaDto();
		Marca m = this.marcaRepository.getById(id);
		marca=m.toDto();
		return marca;
	}


	@Override
	public String update(MarcaDto marca, long clienteId) {
		// TODO Auto-generated method stub
		Marca m = this.marcaRepository.getById(clienteId);
		System.out.println("el id de clientes es " + marca.getIdCliente());
		if(marca.getNombre()!=null){
			m.setNombre(marca.getNombre());
			}
		m.setIdCliente(marca.getIdCliente());
		
		if(marca.isActivo()==true){
			m.setActivo(true);
		}
		try {
			this.marcaRepository.update(m);
			return "Marca Actualizada";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Marca ya existe En la Base de Datos";
		}
	}


	@Override
	public String adicionaMarca(List<MarcaDto> marcaDto, Long idpunto) {
		// TODO Auto-generated method stub
		try{
		this.marcaRepository.agregarMarcaPunto(marcaDto, idpunto);
		return "Marcas adicionadas";
		}catch(Exception e){
		return "No se Pudo Adicionar";
		}
	}


	@Override
	public String inactiveMarca(long marcaId) {
		// TODO Auto-generated method stub
		Marca marca =this.marcaRepository.getById(marcaId);
		marca.setActivo(false);
		try {
			this.marcaRepository.update(marca);
			return "Marca Desactivada";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			return "Marca No se Desactivo";
		}
	}


	@Override
	public String adicionaPunto(long marcaId, long puntoId) {
		return marcaRepository.agregarPuntoAMarca(marcaId, puntoId);
		
		
	}


	@Override
	public String activateMarca(long marcaId) {
		// TODO Auto-generated method stub
		Marca marca =this.marcaRepository.getById(marcaId);
		marca.setActivo(true);
		try {
			this.marcaRepository.update(marca);
			return "Marca Reactivada";
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			return "No se Pudo Reactivar Marca";
		}
	}


	@Override
	public void quitarPunto(long marcaId, long puntoId) {
		// TODO Auto-generated method stub
		this.marcaRepository.quitarPuntoAMarca(marcaId, puntoId);
	}


	@Override
	public List<MarcaDto> getMarcasInactivas() {
		// TODO Auto-generated method stub
		List<Marca> marcas = marcaRepository.getAllInactivos();
		if(marcas !=null && !marcas.isEmpty()){
			List<MarcaDto> dto = new ArrayList<MarcaDto>();
			for(Marca m : marcas){
				dto.add(m.toDtoLess());
			}
			return dto;
		}
		return new ArrayList<MarcaDto>();
	}


	@Override
	public void eliminarPuntos(List<PuntoDto> puntoDto, long marcaId) {
		// TODO Auto-generated method stub
		this.marcaRepository.eliminarPuntosDeMarca(puntoDto,marcaId);
	}


		
	@Override
	public List<MarcaDto> getMarcasProNombre(String nombre) {
		// TODO Auto-generated method stub
		List<Marca> marcas = marcaRepository.getPorNombre(nombre);
		if(marcas !=null && !marcas.isEmpty()){
			List<MarcaDto> dto = new ArrayList<MarcaDto>();
			for(Marca m : marcas){
				dto.add(m.toDtoLess());
			}
			return dto;
		}
		return new ArrayList<MarcaDto>();
	}
	
	
}
