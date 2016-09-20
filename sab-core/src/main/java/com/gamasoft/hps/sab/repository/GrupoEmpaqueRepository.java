package com.gamasoft.hps.sab.repository;

import java.util.List;
import java.util.Set;

import com.gamasoft.hps.sab.domain.Canal;
import com.gamasoft.hps.sab.domain.GrupoEmpaque;
import com.gamasoft.hps.sab.domain.Punto;

public interface GrupoEmpaqueRepository extends Repository<GrupoEmpaque> {
	
	public List<GrupoEmpaque> getByArticulo(Long idArticulo, Boolean activo);
	public GrupoEmpaque getIdByArticuloYNombre(Long idArticulo, String nombre);
	
	public Set<Canal> getCanalesByGrupo(Long idGrupoEmpaque);
	public void agregarCanales(Long idGrupoEmpaque, Set<Canal> canales, boolean reemplazar);
	public void eliminarCanal(Long idGrupoEmpaque, Long idCanal);
	
	public Set<Punto> getPuntosByGrupo(Long idGrupoEmpaque);
	public void agregarPuntos(Long idGrupoEmpaque, Set<Punto> puntos, boolean reemplazar);
	public void eliminarPunto(Long idGrupoEmpaque, Long idPunto);
}
