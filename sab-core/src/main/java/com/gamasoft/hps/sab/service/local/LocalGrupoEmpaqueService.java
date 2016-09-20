package com.gamasoft.hps.sab.service.local;
/**
 * 
 * @author wospino
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Canal;
import com.gamasoft.hps.sab.domain.Empaque;
import com.gamasoft.hps.sab.domain.GrupoEmpaque;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.EmpaqueDto;
import com.gamasoft.hps.sab.dto.GrupoEmpaqueDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ArticuloRepository;
import com.gamasoft.hps.sab.repository.CanalRepository;
import com.gamasoft.hps.sab.repository.ClienteRepository;
import com.gamasoft.hps.sab.repository.EmpaqueRepository;
import com.gamasoft.hps.sab.repository.GrupoEmpaqueRepository;
import com.gamasoft.hps.sab.repository.PuntoRepository;
import com.gamasoft.hps.sab.repository.UnidadRepository;
import com.gamasoft.hps.sab.service.GrupoEmpaqueService;

@Service
public class LocalGrupoEmpaqueService implements GrupoEmpaqueService {

	@Autowired
	private GrupoEmpaqueRepository grupoEmpaqueRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ArticuloRepository articuloRepository;
	
	@Autowired
	private UnidadRepository unidadRepository;
	
	@Autowired
	private EmpaqueRepository empaqueRepository;

	@Autowired
	private CanalRepository canalRepository;
	
	@Autowired
	PuntoRepository puntoRepository;
	
	@Override
	public GrupoEmpaqueDto getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String create(GrupoEmpaqueDto grupoDto) {
		//Validar que el nombre no se repita para el mismo cliente
		GrupoEmpaque existingGrupo =  grupoEmpaqueRepository.getIdByArticuloYNombre(grupoDto.getIdArticulo(), grupoDto.getNombre());
		if(existingGrupo != null){
			return "Ya existe un grupo empaque con el nombre " + grupoDto.getNombre();
		}else{
			GrupoEmpaque grupo = new GrupoEmpaque(grupoDto);
			grupo.setActivo(true);
			grupo.setArticulo(articuloRepository.getById(grupoDto.getIdArticulo()));
			
			grupoEmpaqueRepository.add(grupo);

			return Long.toString(grupo.getId());
		}
	}

	@Override
	public String actualizar(GrupoEmpaqueDto grupoDto) throws RepositoryException {
		//Validar que el nombre no se repita para el mismo cliente
		GrupoEmpaque existingGrupo =  grupoEmpaqueRepository.getIdByArticuloYNombre(grupoDto.getIdArticulo(), grupoDto.getNombre());
		if(existingGrupo != null && !existingGrupo.getId().equals(grupoDto.getId())){
			return "Ya existe un grupo empaque con el nombre " + grupoDto.getNombre();
		}
		
		GrupoEmpaque grupoEmpaque = grupoEmpaqueRepository.getById(grupoDto.getId());
		grupoEmpaque.setNombre(grupoDto.getNombre());
		grupoEmpaque.setPredeterminado(grupoDto.getPredeterminado());
		grupoEmpaqueRepository.update(grupoEmpaque);
		
		return "OK";
	}

	@Override
	public void inactivar(Long id) throws RepositoryException {
		GrupoEmpaque grupo = grupoEmpaqueRepository.getById(id);
		grupo.setActivo(false);
		grupoEmpaqueRepository.update(grupo);

	}

	@Override
	public void activar(Long id) throws RepositoryException {
		GrupoEmpaque grupo = grupoEmpaqueRepository.getById(id);
		grupo.setActivo(true);
		grupoEmpaqueRepository.update(grupo);
	}

	@Override
	public List<GrupoEmpaqueDto> getGrupoEmpaqueByArticulo(Long idArticulo, Boolean activo) {
		List<GrupoEmpaqueDto> gruposDto = new ArrayList<GrupoEmpaqueDto>();
		List<GrupoEmpaque> grupos;

		if(activo){
			grupos = grupoEmpaqueRepository.getByArticulo(idArticulo, true);
		} else {
			grupos = grupoEmpaqueRepository.getByArticulo(idArticulo, false);
		}

		for(GrupoEmpaque ge : grupos){
			gruposDto.add(ge.toDto());
		}

		return gruposDto;
	}

	@Override
	public List<EmpaqueDto> getEmpaquesByGrupo(Long idGrupo, Boolean activo) {
		List<Empaque> empaques = empaqueRepository.getByGrupo(idGrupo, activo);
		List<EmpaqueDto> empaquesDto = new ArrayList<EmpaqueDto>();
		
		for(Empaque e : empaques){
			empaquesDto.add(e.toDto());
		}
		
		return empaquesDto;
	}

	@Override
	public Set<CanalDto> getCanalesByGrupo(Long idGrupoEmpaque) {
		Set<Canal> canales = grupoEmpaqueRepository.getCanalesByGrupo(idGrupoEmpaque);
		Set<CanalDto> canalesDto = new HashSet<CanalDto>();
		for(Canal c : canales){
			canalesDto.add(c.toDto());
		}
		
		return canalesDto;
	}

	@Override
	public Set<PuntoDto> getPuntosByGrupo(Long idGrupoEmpaque) {
		Set<Punto> puntos = grupoEmpaqueRepository.getPuntosByGrupo(idGrupoEmpaque);
		Set<PuntoDto> puntosDto = new HashSet<PuntoDto>();
		for(Punto p : puntos){
			puntosDto.add(p.toDto());
		}
		
		return puntosDto;
	}

	@Override
	public String definirCanales(Long idGrupoEmpaque, Set<CanalDto> canalesDto) {
		Set<Canal> canales = new HashSet<Canal>();
		
		for(CanalDto c : canalesDto){
			Canal canal = canalRepository.getById(c.getId());
			canales.add(canal);
		}
		grupoEmpaqueRepository.agregarCanales(idGrupoEmpaque, canales, true);
	
		return "OK";
	}

	@Override
	public String adicionarCanales(Long idGrupoEmpaque, Set<CanalDto> canalesDto) {
		Set<Canal> canales = new HashSet<Canal>();
		
		for(CanalDto c : canalesDto){
			Canal canal = canalRepository.getById(c.getId());
			canales.add(canal);
		}
		grupoEmpaqueRepository.agregarCanales(idGrupoEmpaque, canales, false);
	
		return "OK";
	}

	@Override
	public String eliminarCanal(Long idGrupoEmpaque, Long idCanal) {
		grupoEmpaqueRepository.eliminarCanal(idGrupoEmpaque, idCanal);
		return "OK";
	}

	@Override
	public String definirPuntos(Long idGrupoEmpaque, Set<PuntoDto> puntosDto) {
		Set<Punto> puntos = new HashSet<Punto>();
		
		for(PuntoDto p : puntosDto){
			Punto punto = puntoRepository.getById(p.getId());
			puntos.add(punto);
		}
		grupoEmpaqueRepository.agregarPuntos(idGrupoEmpaque, puntos, true);
	
		return "OK";
	}

	@Override
	public String adicionarPuntos(Long idGrupoEmpaque, Set<PuntoDto> puntosDto) {
		Set<Punto> puntos = new HashSet<Punto>();
		
		for(PuntoDto p : puntosDto){
			Punto punto = puntoRepository.getById(p.getId());
			puntos.add(punto);
		}
		grupoEmpaqueRepository.agregarPuntos(idGrupoEmpaque, puntos, false);
	
		return "OK";
	}

	@Override
	public String eliminarPunto(Long idGrupoEmpaque, Long idPunto) {
		grupoEmpaqueRepository.eliminarPunto(idGrupoEmpaque, idPunto);
		return "OK";
	}

	@Override
	public String createEmpaque(Long idGrupo, EmpaqueDto empaqueDto){
		GrupoEmpaque grupo = grupoEmpaqueRepository.getById(idGrupo);
		Empaque empaque = new Empaque();
		empaque.setGrupoEmpaque(grupo);
		empaque.setCantidad(empaqueDto.getCantidad());
		empaque.setActivo(true);
		empaque.setArticulo(articuloRepository.getById(empaqueDto.getIdArticulo()));
		empaque.setUnidad(unidadRepository.getById(empaqueDto.getIdUnidad()));
			
		empaqueRepository.add(empaque);
		
		return "OK";
	}

	
	@Override
	public String updateEmpaque(EmpaqueDto empaqueDto) {
		Empaque empaque = empaqueRepository.getById(empaqueDto.getId());
		if(empaque == null){
			return "No existe empaque con id " + empaqueDto.getId();
		}
		
		if(empaqueDto.getIdArticulo() != null){
			empaque.setArticulo(articuloRepository.getById(empaqueDto.getIdArticulo()));
		}
		
		if(empaqueDto.getIdUnidad() != null){
			empaque.setUnidad(unidadRepository.getById(empaqueDto.getIdUnidad()));
		}
		
		empaque.setCantidad(empaqueDto.getCantidad());
		
		try {
			empaqueRepository.update(empaque);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String activarEmpaque(Long idEmpaque) {
		Empaque empaque = empaqueRepository.getById(idEmpaque);
		if(empaque == null){
			return "No existe empaque con id " + idEmpaque;
		}
		empaque.setActivo(true);
		
		try {
			empaqueRepository.update(empaque);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String inactivarEmpaque(Long idEmpaque) {
		Empaque empaque = empaqueRepository.getById(idEmpaque);
		if(empaque == null){
			return "No existe empaque con id " + idEmpaque;
		}
		empaque.setActivo(false);
		
		try {
			empaqueRepository.update(empaque);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String eliminarEmpaque(Long idEmpaque) {
		try {
			empaqueRepository.remove(idEmpaque);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return "Error eliminando empaque";
		}
		return "OK";
	}

}
