/*
 * La plantillas de Hibernate no se usan mas porque Sporing ya no las recomienda y dejaran de ser soportadas (wospino)
 * 
 */
package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.domain.Receta;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.BodegaRepository;
import com.gamasoft.hps.sab.repository.RecetaRepository;

/**
 *
 * @author vascordoba
 */
@Repository
public class HibernateRecetaRepository extends HibernateRepository implements RecetaRepository {

    @Autowired
    public HibernateRecetaRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

	@Override
	@Transactional
	public void add(Receta t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void update(Receta t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Receta getById(long id) {
		Receta r = (Receta) getCurrentSession().get(Receta.class, id);
		Hibernate.initialize(r.getPuntos());
		Hibernate.initialize(r.getCanales());
		Hibernate.initialize(r.getIngredientes());
		
		return r;
		
	}

	@Override
	public List<Receta> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
    
    

}
