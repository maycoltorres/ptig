/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service.local;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.Marca;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.BodegaRepository;
import com.gamasoft.hps.sab.repository.MarcaRepository;
import com.gamasoft.hps.sab.repository.PuntoRepository;
import com.gamasoft.hps.sab.service.PuntoService;

/**
 * 
 * @author wospino
 */
@Service
public class LocalPuntoService implements PuntoService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LocalPuntoService.class);

	private PuntoRepository puntoRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private BodegaRepository bodegaRepository;
	
	@Autowired
	public LocalPuntoService(PuntoRepository puntoRepository, BodegaRepository bodegaRepository) {
		this.puntoRepository = puntoRepository;
		this.bodegaRepository = bodegaRepository;
	}
	
	
	@Override
	public PuntoDto getById(long id) {
		PuntoDto dto = new PuntoDto();
		Punto p = puntoRepository.getById(id);
		try{
		dto =p.toDto2();
		return dto;
	}catch(Exception e){
		return null;
	}}


	@Override
	public List<PuntoDto> getPuntos(Long idCliente, Long Idclienteuser, Long idGrupo, Long idPunto) {
		System.out.println("idcliente es  "+idCliente+" id cleinte user es "+Idclienteuser+" id de grupo es "+idGrupo+" id punto es "+idPunto);
		if(idGrupo == null && idPunto == null && idCliente != null){
			System.out.println("se esta consultando puntos por id cliente");
			List<Punto> puntos = puntoRepository.getByIdCliente(idCliente);
		if (puntos != null && !puntos.isEmpty()) {
			List<PuntoDto> PuntosDto = new ArrayList<PuntoDto>();
			for (Punto c : puntos) {
				PuntosDto.add(c.toDto());
				}
			return PuntosDto;
			}
		}
		if(idGrupo != null && idPunto == null && idCliente != null){
			System.out.println("se esta consultado puntos por id de grupo");
			List<Punto> puntoz = puntoRepository.getPuntoByIdGrupoCliente(idCliente,Idclienteuser, idGrupo, idPunto);
			if (puntoz != null && !puntoz.isEmpty()) {
				List<PuntoDto> PuntosDto = new ArrayList<PuntoDto>();
				for (Punto c : puntoz) {
					PuntosDto.add(c.toDto());
					}
				return PuntosDto;
				}
		}
		if(idPunto != null){
			System.out.println("se esta consultando puntos por id punto");
			List<Punto> puntoz = puntoRepository.getPuntoByIdpunto(idPunto);
			if (puntoz != null && !puntoz.isEmpty()) {
				List<PuntoDto> PuntosDto = new ArrayList<PuntoDto>();
				for (Punto c : puntoz) {
					PuntosDto.add(c.toDto());
					}
				return PuntosDto;
				}
			
		}
		
		
		
		return new ArrayList<PuntoDto>();
	}

	@Override
	public String add(PuntoDto puntoDto, Long idCliente)  {
		Punto c;
		try {
			c = new Punto(puntoDto);
			
			List<Punto> existe=this.puntoRepository.verificarPuntoCliente(puntoDto.getNombre(),puntoDto.getCliente_id());
			if(existe.isEmpty()){
				Set<Bodega> bodegas = new HashSet<Bodega>();
				Bodega bodegaPorDefecto = new Bodega();
				bodegaPorDefecto.setBodegaInterna(true);
				bodegaPorDefecto.setActivo(true);
				bodegaPorDefecto.setNombre("Bodega Interna Punto " + c.getNombre());
				bodegas.add(bodegaPorDefecto);
				c.setBodegas(bodegas);
				
				this.puntoRepository.addpunto(c);
				
				return Long.toString(c.getId());}
			else{
				return "este punto ya existe";
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
//			return "No se pudo crear";
		}

		
	
	}

	@Override
	public void delete(Long PuntoId) {
		try {
			this.puntoRepository.remove(PuntoId);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String update(PuntoDto pdto, long id) {
		// Validacion del json recibido contra el contenido de la BD los puntos
		// diferentes de null seran cambiados en la BD,
		// los campos nulos se asumen que no seran actualizados
		
		Punto punto =this.puntoRepository.getById(id);
		
		List<Punto> existe=this.puntoRepository.verificarPuntoCliente(pdto.getNombre(),punto.getCliente_id());
		if(existe.isEmpty() || existe.get(0).getId()==punto.getId()){
			punto=validaPunto(punto, pdto);
		} else {
			return "Punto ya existe";
		}
		
		try {
			this.puntoRepository.update(punto);
			return "Punto Actualizado";
		} catch (RepositoryException e) {
			e.printStackTrace();
			return "No se ha realizado la actualizacion";
		}
		
	}
	
	@Override
	public String inactivePunto(long id) {
		Punto punto =this.puntoRepository.getById(id);
		punto.setActivo(false);
		try {
			this.puntoRepository.update(punto);
			return "Punto inactivado";
		} catch (RepositoryException e) {
			return "Punto no ha sido inactivado";
		}
		
	}

	@Override
	public String activatePunto(long puntoId) {
		Punto punto =this.puntoRepository.getById(puntoId);
		punto.setActivo(true);
		try {
			this.puntoRepository.update(punto);
			return "Punto Activado";
		} catch (RepositoryException e) {
			return "Punto no ha sido activado";
		}
	}

	@Override
	public List<PuntoDto> getPuntosInactivos(Long idCliente, Long idClienteUser) {
		List<Punto> puntos = puntoRepository.getByIdClienteInactivo(idCliente);
		if (puntos != null && !puntos.isEmpty()) {
			List<PuntoDto> puntosDto = new ArrayList<PuntoDto>();
			for (Punto c : puntos) {
				puntosDto.add(c.toDto());
			}
			
			return puntosDto;}
		return new ArrayList<PuntoDto>();
		
		}
	

	@Override
	public String adicionaMarcas(List<MarcaDto> marcaDto, Long idpunto) {
		this.puntoRepository.agregarMarcasAPunto(marcaDto,idpunto);
		return "El Punto y las Marcas Fueron Guardados";
	}
	
	@Override
	public String adicionaBodegas(List<BodegaDto> bodegas, Long idPunto) throws ServiceException {
		//Validar que el nombre de las bodegas sea unico
		for(BodegaDto b : bodegas){
			if(!bodegaRepository.getByPuntoAndNombre(idPunto, b.getNombre()).isEmpty()){
				return "Ya existe una bodega con el nombre " + b.getNombre();
			}
			
			b.setActivo(true);
			b.setBodegaInterna(false);
		}
		
		this.puntoRepository.agregarBodegasAPunto(bodegas,idPunto);
		
		return "Las bodegas fueron almacenadas";
	}
	
	@Override
	public String actualizarBodega(BodegaDto bodegaDto) {
		List<BodegaDto> bodegas = new ArrayList<BodegaDto>();
		bodegas.add(bodegaDto);
		this.puntoRepository.actualizarBodegas(bodegas);
		
		return "La bodega fue actualizada";
	}

	@Override
	public List<PuntoDto> getPuntPorNombre(String nombre, Long Cliente, Long Grupo, Long iduser) {
		// TODO Auto-generated method stub
		List<Punto> puntos = null;
				puntos= puntoRepository.getByName(nombre, Cliente, Grupo);
		if(puntos !=null && !puntos.isEmpty()){
			List<PuntoDto> dto = new ArrayList<PuntoDto>();
			for(Punto p : puntos){
				dto.add(p.toDto());
			}
			return dto;
		}
		return new ArrayList<PuntoDto>();
	}
	
	public Punto validaPunto(Punto punto, PuntoDto pdto){
		punto.setExtension(pdto.getExtension());
		punto.setTelefono2(pdto.getTelefono2());
		punto.setExtension2(pdto.getExtension2());
		punto.setCentro_costo_id(pdto.getCentro_costo_id());
		punto.setMunicipio_id(pdto.getMunicipio_id());
		punto.setNombre(pdto.getNombre());
		if(pdto.getNombre()!=null){
			punto.setNombre(pdto.getNombre());			
		}
		if(pdto.getDireccion()!=null){
			punto.setDireccion(pdto.getDireccion());
		}
		if(pdto.getTelefono()!=null){
			punto.setTelefono(pdto.getTelefono());
		}
//		System.out.println("el valor de municipio es "+pdto.getMunicipio_id());
//		if(pdto.getMunicipio_id()!=0||Integer.toString(pdto.getMunicipio_id()).length()>0){
//			punto.setMunicipio_id(pdto.getMunicipio_id());
//		}
		if(pdto.getTipo_negocio_id()!=0){
			punto.setTipo_negocio_id(pdto.getTipo_negocio_id());
		}
	
		
		if(pdto.getCliente_id()!=0){
			punto.setCliente_id(pdto.getCliente_id());
		}
		if(pdto.isActivo()==false||pdto.isActivo()==true){
			punto.setActivo(pdto.isActivo());
		}
		
		if(pdto.isControlconspalabra()==false||pdto.isControlconspalabra()==true){
			punto.setControlconspalabra(pdto.isControlconspalabra());
		}
		if(pdto.isImpticketAutoSrv()==false||pdto.isImpticketAutoSrv()==true){
			punto.setImpticketAutoSrv(pdto.isImpticketAutoSrv());
		}
		if(pdto.isInclivaPventa()==false||pdto.isInclivaPventa()==true){
			punto.setInclivaPventa(pdto.isInclivaPventa());
		}
		
		if(pdto.isCapNpersonasAt()==false||pdto.isCapNpersonasAt()==true){
			punto.setCapNpersonasAt(pdto.isCapNpersonasAt());
		}
		if(pdto.isCapCodAutoserv()==false||pdto.isCapCodAutoserv()==true){
			punto.setCapCodAutoserv(pdto.isCapCodAutoserv());
		}
		if(pdto.isCapCodmesero()==false||pdto.isCapCodmesero()==true){
			punto.setCapCodmesero(pdto.isCapCodmesero());
		}
		if(pdto.isDenoatendidopor()==false||pdto.isDenoatendidopor()==true){
			punto.setDenoatendidopor(pdto.isDenoatendidopor());
		}
		if(pdto.isFactFresumido()==false||pdto.isFactFresumido()==true){
			punto.setFactFresumido(pdto.isFactFresumido());
		}
		if(pdto.isFactSinDocVerif()==false||pdto.isFactSinDocVerif()==true){
			punto.setFactSinDocVerif(pdto.isFactSinDocVerif());
		}
		if(pdto.isFvmenseros()==false||pdto.isFvmenseros()==true){
			punto.setFvmenseros(pdto.isFvmenseros());
		}
		if(pdto.isImpremotacpfactura()==false||pdto.isImpremotacpfactura()==true){
			punto.setImpremotacpfactura(pdto.isImpremotacpfactura());
		}
		if(pdto.isClaveanularVerifi()==false||pdto.isClaveanularVerifi()==true){
			punto.setClaveanularVerifi(pdto.isClaveanularVerifi());;
		}
		if(pdto.isApoyalicrec()==false||pdto.isApoyalicrec()==true){
			punto.setApoyalicrec(pdto.isApoyalicrec());
		}
		if(pdto.isAutoUsuEliminar()==false||pdto.isAutoUsuEliminar()==true){
			punto.setAutoUsuEliminar(pdto.isAutoUsuEliminar());
		}
		if(pdto.isContXdenominacion()==false||pdto.isContXdenominacion()==true){
			punto.setContXdenominacion(pdto.isContXdenominacion());
		}
		if(pdto.isFactNpunto()==false||pdto.isFactNpunto()==true){
			punto.setFactNpunto(pdto.isFactNpunto());
		}
		if(pdto.isManNlicencias()==false||pdto.isManNlicencias()==true){
			punto.setManNlicencias(pdto.isManNlicencias());
		}
		if(pdto.isOpLicNred()==false||pdto.isOpLicNred()==true){
			punto.setOpLicNred(pdto.isOpLicNred());
		}
		if(pdto.isCierreocultocaja()==false||pdto.isCierreocultocaja()==true){
			punto.setCierreocultocaja(pdto.isCierreocultocaja());
		}
		if(pdto.isClaveanular()==false||pdto.isClaveanular()==true){
			punto.setClaveanular(pdto.isClaveanular());
		}
		if(pdto.isImprRemotas()==false||pdto.isImprRemotas()==true){
			punto.setImprRemotas(pdto.isImprRemotas());
		}
		if(pdto.isAutAnularVerificacion()==false||pdto.isAutAnularVerificacion()==true){
			punto.setAutAnularVerificacion(pdto.isAutAnularVerificacion());;
		}
		if(pdto.isAlmacenarNpunto()==false||pdto.isAlmacenarNpunto()==true){
			punto.setAlmacenarNpunto(pdto.isAlmacenarNpunto());
		}
		return punto;
		
	}

	@Override
	public String activarBodega(Long idBodega) throws RepositoryException {
		Bodega b = bodegaRepository.getById(idBodega);
		if (b != null){
			b.setActivo(true);
			bodegaRepository.update(b);
		}
		return "OK";
	}

	@Override
	public String desactivarBodega(Long idBodega) throws RepositoryException{
		Bodega b = bodegaRepository.getById(idBodega);
		if (b != null){
			b.setActivo(false);
			bodegaRepository.update(b);
		}
		return "OK";
	}


	@Override
	public List<MarcaDto> getMarcasPorPunto(Long idPunto) {
		return marcaRepository.getMarcasPorPunto(idPunto);
	}


	@Override
	public List<BodegaDto> getBodegasPorPunto(Long idPunto) {
		List<BodegaDto> bodegasDto = new ArrayList<BodegaDto>();
		
		List<Bodega> bodegas = bodegaRepository.getByPuntoAndNombre(idPunto, null);
		for(Bodega b : bodegas){
			bodegasDto.add(b.toDto());
		}
		return bodegasDto;
	}
	
	@Override
	public List<PuntoDto> getPuntosByArticulo(Long idArticulo) {
		List<Punto> puntos = puntoRepository.getByArticulo(idArticulo);
		List<PuntoDto> puntosDto = new ArrayList<PuntoDto>();
		
		for(Punto p : puntos){
			puntosDto.add(p.toDto());
		}
		
		return puntosDto;
	}


	@Override
	public String eliminarMarca(Long idMarca, Long idPunto) {
		try {
			Punto p = puntoRepository.getById(idPunto);
			for (Iterator<Marca> i = p.getMarcas().iterator(); i.hasNext();) {
			    Marca m = i.next();
			    if (m.getId().longValue() == idMarca.longValue()) {
			        i.remove();
			    }
			}
			
			puntoRepository.update(p);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		return "OK";
	}
	
}
