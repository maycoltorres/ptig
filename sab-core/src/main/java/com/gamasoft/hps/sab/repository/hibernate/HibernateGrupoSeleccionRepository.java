package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.GrupoSeleccion;
import com.gamasoft.hps.sab.domain.Seleccion;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.GrupoSeleccionRepository;


@Repository
public class HibernateGrupoSeleccionRepository extends HibernateRepository implements GrupoSeleccionRepository {
	
	@Autowired
	public HibernateGrupoSeleccionRepository(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void add(GrupoSeleccion t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void update(GrupoSeleccion t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public GrupoSeleccion getById(long id) {
		GrupoSeleccion grupo = (GrupoSeleccion) getCurrentSession().get(GrupoSeleccion.class, id);
		
		return grupo;
	}

	@Override
	public List<GrupoSeleccion> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<GrupoSeleccion> getInactivosByCliente(Long idCliente) {
		List<GrupoSeleccion> grupos = getCurrentSession().createQuery("from GrupoSeleccion where activo = false and id_cliente = " + idCliente).list();
		return grupos;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<GrupoSeleccion> getActivosByCliente(Long idCliente) {
		List<GrupoSeleccion> grupos = getCurrentSession().createQuery("from GrupoSeleccion where activo = true and id_cliente = " + idCliente).list();
		return grupos;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Set<Seleccion> getSeleccionesByGrupo(Long idGrupo) {
		GrupoSeleccion grupo = getById(idGrupo);
		Hibernate.initialize(grupo.getSelecciones());
		
		return grupo.getSelecciones();
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void createSelecciones(Long idGrupo, Set<Seleccion> selecciones) {
		GrupoSeleccion grupo = getById(idGrupo);
		for(Seleccion es : grupo.getSelecciones()){
			getCurrentSession().delete(es);
		}
			
		for(Seleccion s : selecciones){
			getCurrentSession().saveOrUpdate(s);
		}
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public int contarByClienteYNombre(Long idCliente, String nombre) {
		String query = "select count(*) from GrupoSeleccion where nombre = '" + nombre + "' and id_cliente = " + idCliente;
		
		return  ((Long) getCurrentSession().createQuery(query).iterate().next()).intValue();
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Long getIdByClienteYNombre(Long idCliente, String nombre) {
		String query = "from GrupoSeleccion where nombre = '" + nombre + "' and id_cliente = " + idCliente;
		List<GrupoSeleccion> grupos = getCurrentSession().createQuery(query).list();
		if(grupos.size() == 0){
			return 0L;
		} else{
			return ((GrupoSeleccion) grupos.get(0)).getId();
		}
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<GrupoSeleccion> getGruposByArticulo(Long idArticulo) {
		List<GrupoSeleccion> grupos = getCurrentSession().createQuery("select g from GrupoSeleccion g inner join g.selecciones s where s.articulo.id = " + idArticulo).list();
		
		return grupos;
	}
	
}
