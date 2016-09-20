package com.gamasoft.hps.sab.service.local;
/**
 * 
 * @author wospino
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Articulo;
import com.gamasoft.hps.sab.domain.GrupoSeleccion;
import com.gamasoft.hps.sab.domain.Seleccion;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.dto.GrupoSeleccionDto;
import com.gamasoft.hps.sab.dto.SeleccionDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ArticuloRepository;
import com.gamasoft.hps.sab.repository.GrupoSeleccionRepository;
import com.gamasoft.hps.sab.repository.UnidadRepository;
import com.gamasoft.hps.sab.service.GrupoSeleccionService;

@Service
public class LocalGrupoSeleccionService implements GrupoSeleccionService {
	
	@Autowired
	private GrupoSeleccionRepository grupoSeleccionRepository;
	
	@Autowired
	private UnidadRepository unidadRepository;
	
	@Autowired
	private ArticuloRepository articuloRepository;

	@Override
	public List<GrupoSeleccionDto> getGrupoSeleccionByCliente(Long idCliente, Boolean activo) {
		List<GrupoSeleccionDto> gruposDto = new ArrayList<GrupoSeleccionDto>();
		List<GrupoSeleccion> grupos;
		
		if(activo){
			grupos = grupoSeleccionRepository.getActivosByCliente(idCliente);
		} else {
			grupos = grupoSeleccionRepository.getInactivosByCliente(idCliente);
		}
		
		for(GrupoSeleccion gs : grupos){
			gruposDto.add(gs.toDto());
		}
		
		return gruposDto;
	}

	@Override
	public String create(GrupoSeleccionDto grupoDto) {
		//Validar que el nombre no se repita para el mismo cliente
		if(grupoSeleccionRepository.contarByClienteYNombre(grupoDto.getIdCliente(), grupoDto.getNombre()) > 0){
			return "Ya existe un grupo de seleccion con el nombre " + grupoDto.getNombre();
		}else{
			GrupoSeleccion grupo = new GrupoSeleccion(grupoDto);
			grupo.setActivo(true);
			grupoSeleccionRepository.add(grupo);
			
			return Long.toString(grupo.getId());
		}
		
	}

	@Override
	public void update(GrupoSeleccionDto grupo) throws RepositoryException {
		//Validar el nombre
		Long idGrupo = grupoSeleccionRepository.getIdByClienteYNombre(grupo.getId(), grupo.getNombre());
		if( idGrupo == 0 || idGrupo == grupo.getId()){
			GrupoSeleccion grupoSeleccion = this.grupoSeleccionRepository.getById(grupo.getId());
			grupoSeleccion.setNombre(grupo.getNombre());
			grupoSeleccion.setTipoGrupo(grupo.getTipoGrupo());
			grupoSeleccion.setCaracteristicaPrecio(grupo.getCaracteristicaPrecio());
			grupoSeleccion.setIncrementoPrecio(grupo.getIncrementoPrecio());
			
			//Actualizar las selecciones si se ha cambiado la caracteristica de precio
			if(grupo.getCaracteristicaPrecio() == GrupoSeleccionDto.INCREMENTO_PRECIO_POR_GRUPO || 
					grupo.getCaracteristicaPrecio() == GrupoSeleccionDto.MAYOR_VALOR){
				for(Seleccion s: grupoSeleccion.getSelecciones()){
					s.setIncrementoPrecio(null);
				}
			}
			
			grupoSeleccionRepository.update(grupoSeleccion);
		} else {
			throw new RepositoryException("Ya existe un grupo de seleccion con el nombre " + grupo.getNombre());
		}
	}

	@Override
	public void inactivar(Long id) throws RepositoryException {
		GrupoSeleccion grupoSeleccion = this.grupoSeleccionRepository.getById(id);
		grupoSeleccion.setActivo(false);
		
		this.grupoSeleccionRepository.update(grupoSeleccion);
	}

	@Override
	public void activar(Long id) throws RepositoryException {
		GrupoSeleccion grupoSeleccion = this.grupoSeleccionRepository.getById(id);
		grupoSeleccion.setActivo(true);
		
		this.grupoSeleccionRepository.update(grupoSeleccion);
	}

	@Override
	public GrupoSeleccionDto getById(Long id) {
		GrupoSeleccion grupoSeleccion = this.grupoSeleccionRepository.getById(id);
		
		return grupoSeleccion.toFullDto();
	}

	@Override
	public List<SeleccionDto> getSeleccionesByGrupo(Long idGrupo) {
		List<SeleccionDto> seleccionesDto = new ArrayList<SeleccionDto>();
		Set<Seleccion> selecciones = this.grupoSeleccionRepository.getSeleccionesByGrupo(idGrupo);
		for(Seleccion s : selecciones){
			seleccionesDto.add(s.toDto());
		}
		
		return seleccionesDto;
	}

	@Override
	public String createSeleccion(Long idGrupo, Set<SeleccionDto> seleccionesDto) {
		Set<Seleccion> selecciones = new HashSet<Seleccion>();
		GrupoSeleccion grupo = grupoSeleccionRepository.getById(idGrupo);
		
		for(SeleccionDto dto : seleccionesDto){
			if(grupo.getCaracteristicaPrecio() != GrupoSeleccionDto.INCREMENTO_PRECIO_POR_ARTICULO &&
					dto.getIncrementoPrecio() != null){
				return "No se puede definir incremento de precio por articulo para el tipo de grupo";
			}
			
			Seleccion s = new Seleccion(dto);
			Unidad u = unidadRepository.getById(dto.getIdUnidad());
			Articulo a = articuloRepository.getById(dto.getIdArticulo());
			
			s.setUnidad(u);
			s.setArticulo(a);
			s.setGrupoSeleccion(grupo);
			
			selecciones.add(s);
		}
		
		grupoSeleccionRepository.createSelecciones(idGrupo, selecciones);
		return "OK";
	}

	@Override
	public List<GrupoSeleccionDto> getGrupoSeleccionByArticulo(Long idArticulo) {
		List<GrupoSeleccionDto> gruposDto = new ArrayList<GrupoSeleccionDto>();
		List<GrupoSeleccion> grupos = grupoSeleccionRepository.getGruposByArticulo(idArticulo);
		for(GrupoSeleccion g : grupos){
			gruposDto.add(g.toDto());
		}
		
		return gruposDto;
	} 
	
}
