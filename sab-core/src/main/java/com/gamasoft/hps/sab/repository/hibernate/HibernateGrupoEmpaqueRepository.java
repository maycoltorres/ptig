package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Canal;
import com.gamasoft.hps.sab.domain.GrupoEmpaque;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.GrupoEmpaqueRepository;


@Repository
public class HibernateGrupoEmpaqueRepository extends HibernateRepository implements GrupoEmpaqueRepository {
	
	@Autowired
	public HibernateGrupoEmpaqueRepository(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void add(GrupoEmpaque t) {
		getCurrentSession().saveOrUpdate(t);
		
	}

	@Override
	@Transactional
	public void update(GrupoEmpaque t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public GrupoEmpaque getById(long id) {
		return (GrupoEmpaque) getSessionFactory().getCurrentSession().get(GrupoEmpaque.class, id);
	}

	@Override
	public List<GrupoEmpaque> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<GrupoEmpaque> getByArticulo(Long idArticulo, Boolean activo) {
		String query = "from GrupoEmpaque where articulo.id = " + idArticulo;
		if(activo != null){
			if(activo == true){
				query = query + " and activo = 1";
			}else{
				query = query + " and activo = 0";
			}
		}
			
		List<GrupoEmpaque> grupos = getCurrentSession().createQuery(query).list();
		return grupos;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public GrupoEmpaque getIdByArticuloYNombre(Long idArticulo, String nombre) {
		String query = "from GrupoEmpaque where nombre = '" + nombre + "' and articulo.id = " + idArticulo;
		List<GrupoEmpaque> grupos = getCurrentSession().createQuery(query).list();
		if(grupos.size() == 0){
			return null;
		} else{
			return (GrupoEmpaque) grupos.get(0);
		}
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Set<Canal> getCanalesByGrupo(Long idGrupoEmpaque) {
		GrupoEmpaque grupo = getById(idGrupoEmpaque);
		if(grupo!=null){
			Hibernate.initialize(grupo.getCanales());
			return grupo.getCanales();
		}
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Set<Punto> getPuntosByGrupo(Long idGrupoEmpaque) {
		GrupoEmpaque grupo = getById(idGrupoEmpaque);
		if(grupo!=null){
			Hibernate.initialize(grupo.getPuntos());
			return grupo.getPuntos();
		}
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void agregarCanales(Long idGrupoEmpaque, Set<Canal> canales,
			boolean reemplazar) {
		GrupoEmpaque grupo = getById(idGrupoEmpaque);
		if(grupo.getCanales() == null){
			grupo.setCanales(canales);
		}else if(reemplazar){
			grupo.setCanales(canales);
		} else{
			grupo.getCanales().addAll(canales);
		}
		
		getCurrentSession().saveOrUpdate(grupo);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void eliminarCanal(Long idGrupoEmpaque, Long idCanal) {
		GrupoEmpaque grupo = getById(idGrupoEmpaque);
		Set<Canal> canales = grupo.getCanales();
		
		for(Canal c : canales){
			if(c.getId().equals(idCanal)){
				canales.remove(c);
			}
		}
		getCurrentSession().saveOrUpdate(grupo);
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void agregarPuntos(Long idGrupoEmpaque, Set<Punto> puntos,
			boolean reemplazar) {
		GrupoEmpaque grupo = getById(idGrupoEmpaque);
		if(grupo.getPuntos() == null){
			grupo.setPuntos(puntos);
		}else if(reemplazar){
			grupo.setPuntos(puntos);
		} else{
			grupo.getPuntos().addAll(puntos);
		}
		
		getCurrentSession().saveOrUpdate(grupo);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void eliminarPunto(Long idGrupoEmpaque, Long idPunto) {
		GrupoEmpaque grupo = getById(idGrupoEmpaque);
		Set<Punto> puntos = grupo.getPuntos();
		
		for(Punto p : puntos){
			if(p.getId().equals(idPunto)){
				puntos.remove(p);
			}
		}
		getCurrentSession().saveOrUpdate(grupo);
	}
	
}
