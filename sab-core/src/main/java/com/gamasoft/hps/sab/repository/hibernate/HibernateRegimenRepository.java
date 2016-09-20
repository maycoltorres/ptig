package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Impuesto;
import com.gamasoft.hps.sab.domain.Receta;
import com.gamasoft.hps.sab.domain.Regimen;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ImpuestoRepository;
import com.gamasoft.hps.sab.repository.RegimenRepository;
/**
*
* @author wospino
*/
@Repository
public class HibernateRegimenRepository extends HibernateRepository implements RegimenRepository{
	
	@Autowired
	public HibernateRegimenRepository(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Regimen> getAll() {
		List<Regimen> regimenes = getCurrentSession().createQuery("from Regimen").list();
		return regimenes;
	}

	@Override
	public void add(Regimen t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Regimen t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Regimen getById(long id) {
		return (Regimen) getCurrentSession().get(Regimen.class, id);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Regimen> getAllByImpuesto(Long idImpuesto) {
		List<Regimen> regimenes = getCurrentSession().createQuery("from Regimen where impuesto.id = " + idImpuesto ).list();
		return regimenes;
	}	
}
