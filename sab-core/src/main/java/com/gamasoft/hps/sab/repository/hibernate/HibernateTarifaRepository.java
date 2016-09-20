package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Regimen;
import com.gamasoft.hps.sab.domain.Tarifa;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.TarifaRepository;
/**
*
* @author wospino
*/
@Repository
public class HibernateTarifaRepository extends HibernateRepository implements TarifaRepository{
	
	@Autowired
	public HibernateTarifaRepository(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(Tarifa t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Tarifa t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Tarifa getById(long id) {
		return (Tarifa) getCurrentSession().get(Tarifa.class, id);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Tarifa> getAll() {
		List<Tarifa> tarifas = getCurrentSession().createQuery("from Tarifa").list();
		return tarifas;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Tarifa> getByRegimen(Long idRegimen) {
		List<Tarifa> tarifas = getCurrentSession().createQuery("from Tarifa where regimen.id = " + idRegimen).list();
		return tarifas;
	}	
}
