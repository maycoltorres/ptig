package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Empaque;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.EmpaqueRepository;


@Repository
public class HibernateEmpaqueRepository extends HibernateRepository implements EmpaqueRepository {
	
	@Autowired
	public HibernateEmpaqueRepository(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void add(Empaque t) {
		getCurrentSession().save(t);
		
	}

	@Override
	@Transactional
	public void update(Empaque t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
		
	}

	@Override
	@Transactional
	public void remove(long id) throws RepositoryException {
		getCurrentSession().delete(this.getById(id));
	}

	@Override
	@Transactional
	public Empaque getById(long id) {
		return (Empaque) getSessionFactory().getCurrentSession().get(Empaque.class, id);
		
	}

	@Override
	public List<Empaque> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Empaque> getByGrupo(Long idGrupoEmpaque, Boolean activo) {
		String query = "from Empaque where grupoEmpaque.id=" + idGrupoEmpaque;
		if(activo != null){
			if(activo == true){
				query = query + " and activo = 1";
			}else{
				query = query + " and activo = 0";
			}
		}
		List<Empaque> empaques = getCurrentSession().createQuery(query).list();
		return empaques;
	}

}
