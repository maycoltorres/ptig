/*
 * La plantillas de Hibernate no se usan mas porque Sporing ya no las recomienda y dejaran de ser soportadas (wospino)
 * 
 */
package com.gamasoft.hps.sab.repository.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Articulo;
import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.Marca;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.BodegaRepository;

/**
 *
 * @author vascordoba
 */
@Repository
public class HibernateBodegaRepository extends HibernateRepository implements BodegaRepository {

    @Autowired
    public HibernateBodegaRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

	@Override
	public void add(Bodega t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void update(Bodega t) throws RepositoryException {
		 getCurrentSession().saveOrUpdate(t);
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Bodega getById(long id) {
		Bodega bodega =  (Bodega) getCurrentSession().get(Bodega.class, id);
		return bodega;
	}

	@Override
	public List<Bodega> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Bodega> getByPuntoAndNombre(Long idPunto, String nombre) {
		String query = "select b from Bodega b where b.punto.id = "+ idPunto;
		if(nombre != null){
			query = query + " and nombre = '" + nombre + "'";
		}
		List<Bodega> bodegas = getCurrentSession().createQuery(query).list();
		
		return bodegas;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Bodega> getByArticulo(Long idArticulo, String mostrar) {
		StringBuilder query = new StringBuilder("select b from Articulo a inner join a.puntos p inner join p.bodegas b where a.id=");
		query.append(idArticulo);
		String operador = " and ";
	
		if(mostrar != null){
			if(mostrar.equalsIgnoreCase("activos")){
				query.append(operador);
				query.append(" b.activo = 1");
			} else if (mostrar.equalsIgnoreCase("inactivos")){
				query.append(operador);
				query.append(" b.activo = 0");
			}
		}
		
		List<Bodega> bodegas = getCurrentSession().createQuery(query.toString()).list();
		
		return bodegas;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Bodega> getByCliente(Long idCliente, String mostrar) {
		StringBuilder query = new StringBuilder("select b from Punto p inner join p.bodegas b where p.cliente_id = ");
		query.append(idCliente);
		String operador = " and ";
	
		if(mostrar != null){
			if(mostrar.equalsIgnoreCase("activos")){
				query.append(operador);
				query.append(" b.activo = 1");
			} else if (mostrar.equalsIgnoreCase("inactivos")){
				query.append(operador);
				query.append(" b.activo = 0");
			}
		}
		
		List<Bodega> bodegas = getCurrentSession().createQuery(query.toString()).list();
		
		return bodegas;
	}
	
}

