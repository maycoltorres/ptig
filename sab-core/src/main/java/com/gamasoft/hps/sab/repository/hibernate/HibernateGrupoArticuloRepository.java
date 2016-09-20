package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.GrupoArticulo;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.GrupoArticuloRepository;
/**
*
* @author wospino
*/
@Repository
public class HibernateGrupoArticuloRepository extends HibernateRepository implements GrupoArticuloRepository{
	
	@Autowired
	public HibernateGrupoArticuloRepository(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<GrupoArticulo> getAllByClase(Long idClase) {
		List<GrupoArticulo> grupos = getCurrentSession().createQuery("from GrupoArticulo where claseArticulo.id = " + idClase).list();
		return grupos;
	}

	@Override
	public void add(GrupoArticulo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GrupoArticulo t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public GrupoArticulo getById(long id) {
		return (GrupoArticulo) getSessionFactory().getCurrentSession().get(GrupoArticulo.class, id);
	}

	@Override
	public List<GrupoArticulo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<GrupoArticulo> getByClaseYNombre(Long idClase, String nombre) {
		List<GrupoArticulo> grupos = getCurrentSession().createQuery("from GrupoArticulo where claseArticulo.id = " + idClase + " and nombre = ' " + nombre + "'").list();
		return grupos;
	}	
}
