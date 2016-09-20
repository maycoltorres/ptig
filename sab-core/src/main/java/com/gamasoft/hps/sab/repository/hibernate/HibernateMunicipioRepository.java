package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Municipio;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.MunicipioRepository;
@Repository
public class HibernateMunicipioRepository extends HibernateRepository implements MunicipioRepository{

	
	@Autowired
    public HibernateMunicipioRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
	
	@Override
	public void add(Municipio t) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Municipio t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Municipio getById(long id) {
		// TODO Auto-generated method stub
		 return (Municipio) getSessionFactory().getCurrentSession().get(Municipio.class, id);
	}

	@Override
	@Transactional
	public List<Municipio> getAll() {
		// TODO Auto-generated method stub
      @SuppressWarnings("unchecked")
		List<Municipio> Municipios = getCurrentSession().createQuery("from Municipio").list();
        return Municipios;
	}

	@Override
	@Transactional
	public List<Municipio> getByDpto(long dptoId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Municipio> municipio = getCurrentSession().createQuery("from Municipio where departamento_id="+dptoId).list();
		return municipio;
	}

}
