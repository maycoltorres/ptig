package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Venta;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.VentaRepository;


@Repository
public class HibernateVentaRepository extends HibernateRepository implements VentaRepository {
	
	@Autowired
	public HibernateVentaRepository(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void add(Venta t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void update(Venta t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Venta getById(long id) {
		return null;
	}

	@Override
	public List<Venta> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
