/*
 * La plantillas de Hibernate no se usan mas porque Sporing ya no las recomienda y dejaran de ser soportadas (wospino)
 * 
 */
package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.domain.UnidadVenta;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.BodegaRepository;
import com.gamasoft.hps.sab.repository.UnidadVentaRepository;

/**
 *
 * @author vascordoba
 */
@Repository
public class HibernateUnidadVentaRepository extends HibernateRepository implements UnidadVentaRepository {

    @Autowired
    public HibernateUnidadVentaRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }


	@Override
	public void add(UnidadVenta t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(UnidadVenta t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public UnidadVenta getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<UnidadVenta> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<UnidadVenta> getByArticulo(Long idArticulo) {
		String query = "select u from UnidadVenta u where u.venta.articulo.id = "+ idArticulo;
		List<UnidadVenta> unidades = getCurrentSession().createQuery(query).list();
		
		return unidades;
	}
	
}

	




