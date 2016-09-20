package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.ConfiguracionArticulos;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ConfiguracionArticulosRepository;

@Repository
public class HibernateConfiguracionArticulosRepository extends HibernateRepository implements ConfiguracionArticulosRepository{

	  @Autowired
	    public HibernateConfiguracionArticulosRepository(SessionFactory sessionFactory) {
	        setSessionFactory(sessionFactory);
	    }

	@Override
	@Transactional
	public void add(ConfiguracionArticulos t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void update(ConfiguracionArticulos t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConfiguracionArticulos getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfiguracionArticulos> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ConfiguracionArticulos getByCliente(Long id) {
		List<ConfiguracionArticulos> configs = getCurrentSession().createQuery(
				"from ConfiguracionArticulos where id_cliente = " + id).list();

		if (configs.size() > 0){
			return configs.get(0);
		}
		
		return null;
	}
	
	
}
