package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Pais;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.PaisRepository;
import com.gamasoft.hps.sab.repository.UnidadRepository;

@Repository
public class HibernateUnidadRepository extends HibernateRepository implements UnidadRepository {

	  @Autowired
	    public HibernateUnidadRepository(SessionFactory sessionFactory) {
	        setSessionFactory(sessionFactory);
	    }
	
	@Override
	@Transactional
	public void add(Unidad t) {
		getCurrentSession().save(t);
	} 

	@Override
	public void update(Unidad t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Unidad getById(long id) {
		 return (Unidad) getSessionFactory().getCurrentSession().get(Unidad.class, id);
		
	}
	@Override
	@Transactional
	public List<Unidad> getAll() {
        @SuppressWarnings("unchecked")
		List<Unidad> unidades = getCurrentSession().createQuery("from Unidad").list();
        return unidades;
    }

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Unidad> getUnidadesByCriteria(Long idCliente, String tipo, String mostrar) {
		StringBuffer query = new StringBuffer("from Unidad where idCliente in (0");
		String separador = " and ";
		
		if(idCliente != null){
			query.append(",");
			query.append(idCliente);
		}
		query.append(")");
		
		if(tipo != null){
			query.append(separador);
			query.append(" tipo='" );
			query.append(tipo);
			query.append("' ");
		}
		
		if(mostrar != null){
			if(mostrar.equalsIgnoreCase("estandar")){
				query.append(separador);
				query.append(" estandar = true ");
			} else if (mostrar.equalsIgnoreCase("propias")){
				query.append(separador);
				query.append(" estandar = false ");
			}
		}
		
		List<Unidad> unidades = getCurrentSession().createQuery(query.toString()).list();
		
		return unidades;
	}

}
