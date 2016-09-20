package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.GrupoArticulo;
import com.gamasoft.hps.sab.domain.Impuesto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ImpuestoRepository;
/**
*
* @author wospino
*/
@Repository
public class HibernateImpuestoRepository extends HibernateRepository implements ImpuestoRepository{
	
	@Autowired
	public HibernateImpuestoRepository(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	@Transactional
	public List<Impuesto> getAll() {
		@SuppressWarnings("unchecked")
		List<Impuesto> impuestos = getCurrentSession().createQuery("from Impuesto").list();
		return impuestos;
	}

	@Override
	public void add(Impuesto t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Impuesto t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Impuesto getById(long id) {
		Impuesto impuesto = (Impuesto) getSessionFactory().getCurrentSession().get(Impuesto.class, id);
		
		return impuesto;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Impuesto> getAllByPais(Long idPais) {
		List<Impuesto> impuestos = getCurrentSession().createQuery("from Impuesto where idPais = " + idPais ).list();
		return impuestos;
	}

}
