/*
 * La plantillas de Hibernate no se usan mas porque Sporing ya no las recomienda y dejaran de ser soportadas (wospino)
 * 
 */
package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.ListaPrecios;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ListaPreciosRepository;

/**
 *
 * @author vascordoba
 */
@Repository
public class HibernateListaPreciosRepository extends HibernateRepository implements ListaPreciosRepository {

    @Autowired
    public HibernateListaPreciosRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

	@Override
	@Transactional
	public void add(ListaPrecios t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void update(ListaPrecios t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void remove(long id) throws RepositoryException {
		getCurrentSession().delete(getById(id));
		
	}

	@Override
	@Transactional
	public ListaPrecios getById(long id) {
		ListaPrecios lista = (ListaPrecios) getCurrentSession().get(ListaPrecios.class, id);
		return lista;
	}

	@Override
	public List<ListaPrecios> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<ListaPrecios> getByCliente(Long idCliente, Boolean activo) {
		String query = "from ListaPrecios where cliente.id = " + idCliente;
		if(activo != null){
			if(activo == true){
				query = query + " and activo = 1";
			}else{
				query = query + " and activo = 0";
			}
		}
		List<ListaPrecios> lista = getCurrentSession().createQuery(query).list();
		
		return lista;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public ListaPrecios getByClienteYNombre(Long idCliente, String nombre) {
		List<ListaPrecios> listas = getCurrentSession().createQuery("from ListaPrecios where cliente.id = " + idCliente + " and nombre='" + nombre + "'").list();
		if(listas.size()>0){
			return listas.get(0);
		}
		
		return null;
	}
    
}

