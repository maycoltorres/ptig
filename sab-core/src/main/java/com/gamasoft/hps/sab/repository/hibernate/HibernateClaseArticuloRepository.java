package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import javax.persistence.UniqueConstraint;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.ClaseArticulo;
import com.gamasoft.hps.sab.domain.ListaPrecios;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ClaseArticuloRepository;
/**
*
* @author wospino
*/
@Repository
public class HibernateClaseArticuloRepository extends HibernateRepository implements ClaseArticuloRepository{
	
	@Autowired
	public HibernateClaseArticuloRepository(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ClaseArticulo> getAllByCliente(Long idCliente, Boolean venta, Boolean inventario, Boolean activo) {
		String query = "from ClaseArticulo where idCliente = " + idCliente;
		if(venta != null){
			if (venta == true){
				query = query + " and venta = true ";
			} else{
			query = query + " and venta = false ";
			}
		}
		
		if(inventario != null){
			if (inventario == true){
				query = query + " and inventario = true ";
			}else{
				query = query + " and inventario = false ";
			}
		}
		
		if(activo != null){
			if(activo == true){
				query = query + " and activo = true";
			}else{
				query = query + " and activo = false";
			}
		}
		
		List<ClaseArticulo> clases = getCurrentSession().createQuery(query).list();
		
		return clases;
	}

	@Override
	@Transactional
	public void add(ClaseArticulo t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void update(ClaseArticulo t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public ClaseArticulo getById(long id) {
		ClaseArticulo clase = (ClaseArticulo) getCurrentSession().get(ClaseArticulo.class, id);
		Hibernate.initialize(clase.getGrupos());
		return clase;
	}

	@Override
	public List<ClaseArticulo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ClaseArticulo getByClienteYNombre(Long idCliente, String nombre) {
		String query = "from ClaseArticulo where idCliente = " + idCliente + " and nombre = '" + nombre + "'";
		List<ClaseArticulo> results = getCurrentSession().createQuery(query).list();
		if(results.size()>0){
			return results.get(0);
		}
		
		return null;
	}	
}
