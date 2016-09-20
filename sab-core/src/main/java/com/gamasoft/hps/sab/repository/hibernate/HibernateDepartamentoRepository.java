package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Departamento;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.DepartamentoRepository;

@Repository
public class HibernateDepartamentoRepository extends HibernateRepository implements DepartamentoRepository {

	 @Autowired
	 public HibernateDepartamentoRepository(SessionFactory sessionFactory) {
	        setSessionFactory(sessionFactory);
	    }
	
	@Override
	public void add(Departamento t) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Departamento t) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Departamento getById(long id) {
		// TODO Auto-generated method stub
		return (Departamento) getCurrentSession().get(Departamento.class, id);
		
	}

	@Override
	@Transactional
	public List<Departamento> getAll() {
		// TODO Auto-generated method stub
		  @SuppressWarnings("unchecked")
		List<Departamento> Dptos = getCurrentSession().createQuery("from Departamento").list();
	        return Dptos;
	}
	
	@Override
	@Transactional
	public List<Departamento> getdpto(Long id){
		
		@SuppressWarnings("unchecked")
		List<Departamento> dpto = getCurrentSession().createQuery("from Departamento where pais_id="+id).list();
		return dpto;
	
		
	}

	@Override
	@Transactional
	public List<Departamento> getdptoIdmunicipio(Long idMunicipio) {
		// TODO Auto-generated method stub
	
		@SuppressWarnings("unchecked")
		List<Departamento> dpto = getCurrentSession().createQuery("select d from Departamento d, Municipio m  where m="+idMunicipio+" and d.id=m.departamento_id").list();
//		select * from departamento, municipio  where departamento.id=municipio.departamento_id and municipio.id=868 ;
		return dpto;
	}

	
	
	
}
