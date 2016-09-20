package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.Canal;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.CanalRepository;
/**
*
* @author wospino
*/
@Repository
public class HibernateCanalRepository extends HibernateRepository implements CanalRepository{
	
	@Autowired
	public HibernateCanalRepository(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	@Transactional
	public List<Canal> getAll() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Canal> canales = getCurrentSession().createQuery("from Canal").list();
		return canales;
	}

	@Override
	public void add(Canal t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Canal t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Canal getById(long id) {
		Canal canal =  (Canal) getCurrentSession().get(Canal.class, id);
		return canal;
	}	
}
