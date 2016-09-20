package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




import com.gamasoft.hps.sab.domain.Articulo;
import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.Inventario;
import com.gamasoft.hps.sab.domain.MaximosMinimos;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ArticuloRepository;
import com.gamasoft.hps.sab.repository.InventarioRepository;


@Repository
public class HibernateInventarioRepository extends HibernateRepository implements InventarioRepository {
	
	@Autowired
	public HibernateInventarioRepository(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void add(Inventario t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void update(Inventario t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Inventario getById(long id) {
		return null;
	}

	@Override
	public List<Inventario> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
