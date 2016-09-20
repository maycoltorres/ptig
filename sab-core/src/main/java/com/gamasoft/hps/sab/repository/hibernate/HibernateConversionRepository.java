package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Conversion;
import com.gamasoft.hps.sab.domain.Pais;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ConversionRepository;
import com.gamasoft.hps.sab.repository.PaisRepository;
import com.gamasoft.hps.sab.repository.UnidadRepository;

@Repository
public class HibernateConversionRepository extends HibernateRepository implements ConversionRepository {
	  
	@Autowired
    public HibernateConversionRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
	
	@Override
	@Transactional
	public Conversion getById(long id) {
		 return (Conversion) getSessionFactory().getCurrentSession().get(Conversion.class, id);
		
	}

	@Override
	@Transactional
	public void add(Conversion t) {
		getCurrentSession().save(t);
	}

	@Override
	@Transactional
	public void update(Conversion t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Conversion> getAllByUnidadOrigen(Long idUnidadOrigen) {
		List<Conversion> conversiones = getCurrentSession().createQuery("from Conversion where unidadOrigen.id = " + idUnidadOrigen).list();
        return conversiones;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Conversion> getAllByUnidadDestino(Long idUnidadDestino) {
		List<Conversion> conversiones = getCurrentSession().createQuery("from Conversion where unidadDestino.id = " + idUnidadDestino).list();
        return conversiones;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Conversion> getByOrigenYDestino(Long idUnidadOrigen,
			Long idUnidadDestino) {
		List<Conversion> conversiones = getCurrentSession().createQuery("from Conversion where unidadOrigen.id = " + idUnidadOrigen  + 
				" and unidadDestino.id = " + idUnidadDestino).list();
        return conversiones;
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Conversion> getAll() {
		List<Conversion> conversiones = getCurrentSession().createQuery("from Conversion").list();
		return conversiones;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Conversion> getAllByClienteYArticulo(Long idCliente, Long idArticulo) {
		StringBuffer query = new StringBuffer("from Conversion where unidadOrigen.idCliente in (0, ");
		query.append(idCliente);
		query.append(") ");
		
		if(idArticulo != null){
			query.append(" and (articulo.id = ");
			query.append(idArticulo);
			query.append(" or articulo is null)");
		}
		
		List<Conversion> conversiones = getCurrentSession().createQuery(query.toString()).list();
		
		return conversiones;
	}

}
