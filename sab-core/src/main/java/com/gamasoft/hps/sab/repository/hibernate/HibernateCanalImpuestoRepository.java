package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.CanalImpuesto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.CanalImpuestoRepository;


@Repository
public class HibernateCanalImpuestoRepository extends HibernateRepository implements CanalImpuestoRepository {
	
	@Autowired
	public HibernateCanalImpuestoRepository(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void add(CanalImpuesto t) {
		getCurrentSession().save(t);
		
	}

	@Override
	@Transactional
	public void update(CanalImpuesto t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
		
	}

	@Override
	@Transactional
	public void remove(long id) throws RepositoryException {
		CanalImpuesto c = getById(id);
		getCurrentSession().delete(c);
	}

	@Override
	@Transactional
	public CanalImpuesto getById(long id) {
		return (CanalImpuesto) getSessionFactory().getCurrentSession().get(CanalImpuesto.class, id);
		
	}

	@Override
	public List<CanalImpuesto> getAll() {
		// TODO Auto-generated method stub
		return null;
		
	}

	
	/*
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Empaque> getByGrupo(Long idGrupoEmpaque) {
		String query = "from Empaque where grupoEmpaque.id=" + idGrupoEmpaque;
		List<Empaque> empaques = getCurrentSession().createQuery(query).list();
		return empaques;
	}*/

}
