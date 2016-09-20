package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.ConfiguracionCliente;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ConfiguracionClienteRepository;
/**
*
* @author wospino
*/
@Repository
public class HibernateConfiguracionClienteRepository extends HibernateRepository implements ConfiguracionClienteRepository{
	
	@Autowired
	public HibernateConfiguracionClienteRepository(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void add(ConfiguracionCliente t) {
		getCurrentSession().save(t);
		
	}

	@Override
	@Transactional
	public void update(ConfiguracionCliente t) throws RepositoryException {
		getCurrentSession().update(t);
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ConfiguracionCliente getById(long id) {
		return (ConfiguracionCliente) getCurrentSession().get(ConfiguracionCliente.class, id);
	}

	@Override
	public List<ConfiguracionCliente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ConfiguracionCliente getByCliente(Long idCliente) {
		List<ConfiguracionCliente> conf = getCurrentSession().createQuery("from ConfiguracionCliente where cliente.id = "+ idCliente).list();
		if(!conf.isEmpty()){
			return conf.get(0);
		}
		
		return null;
	}
	
}
