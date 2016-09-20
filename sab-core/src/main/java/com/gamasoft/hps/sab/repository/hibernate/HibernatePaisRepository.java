package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Pais;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.PaisRepository;

@Repository
public class HibernatePaisRepository extends HibernateRepository implements PaisRepository {

	  @Autowired
	    public HibernatePaisRepository(SessionFactory sessionFactory) {
	        setSessionFactory(sessionFactory);
	    }
	
	@Override
	public void add(Pais t) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Pais t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Pais getById(long id) {
		 return (Pais) getSessionFactory().getCurrentSession().get(Pais.class, id);
		
	}
	@Override
	@Transactional
	public List<Pais> getAll() {
        @SuppressWarnings("unchecked")
		List<Pais> Paises =getCurrentSession().createQuery("from Pais").list();
        return Paises;
    }

	@Override
	public List<Pais> getPaisIdDepartamento(Long idDepartamento) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Pais> dpto = getCurrentSession().createQuery("select p from Pais p, Departamento d  where d="+idDepartamento+" and p.id=d.pais_id").list();

		return dpto;
	}
}
