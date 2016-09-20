package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;





import com.gamasoft.hps.sab.domain.Articulo;
import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.Ingrediente;
import com.gamasoft.hps.sab.domain.MaximosMinimos;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.domain.VentaCanalImpuesto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ArticuloRepository;
import com.gamasoft.hps.sab.repository.IngredienteRepository;


@Repository
public class HibernateIngredienteRepository extends HibernateRepository implements IngredienteRepository {
	
	@Autowired
	public HibernateIngredienteRepository(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	@Transactional
	public void add(Ingrediente t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	@Transactional
	public void update(Ingrediente t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
	}

	@Override
	@Transactional
	public Ingrediente getById(long id) {
		Ingrediente ingrediente = (Ingrediente) getCurrentSession().get(Ingrediente.class, id);
		Hibernate.initialize(ingrediente.getArticulo());
		
		return ingrediente;
	}

	@Override
	public List<Ingrediente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
