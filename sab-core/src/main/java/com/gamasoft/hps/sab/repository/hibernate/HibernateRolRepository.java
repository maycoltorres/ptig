package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Rol;
import com.gamasoft.hps.sab.domain.RolCliente;
import com.gamasoft.hps.sab.domain.Transaccion;
import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.RolRepository;
@Repository
public class HibernateRolRepository extends HibernateRepository implements RolRepository {

	 @Autowired
	    public HibernateRolRepository(SessionFactory sessionFactory) {
	        setSessionFactory(sessionFactory);
	    }
		
	@Override
	@Transactional
	public void add(Rol r) {
		Validate.notNull(r, "El punto no puede estar nulo");
        r.setActivo(true);
        getCurrentSession().saveOrUpdate(r);
		
	}

	@Override
	@Transactional
	public void update(Rol r) throws RepositoryException {
		Validate.notNull(r, "The rol no puede estar nulo");
        getCurrentSession().saveOrUpdate(r);
	}

	@Override
	@Transactional
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Rol getById(long id) {
		Rol r =(Rol)getCurrentSession().load(Rol.class, id);
    	System.out.println(""+r.getRolcliente().size());
    	return r;
	}

	@Override
	@Transactional
	public List<Rol> getAll() {
		@SuppressWarnings("unchecked")
		List<Rol> roles = getCurrentSession().createQuery("from Rol where Activo=1").list();
        return roles;
	}


	@Override
	@Transactional
	public List<Rol> getInactived() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Rol> roles = getCurrentSession().createQuery("from Rol where Activo=0").list();
        return roles;
	}


	@Override
	@Transactional
	public List<Rol> getByname(String nombre, Long cliente, Long grupo) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Rol> rol =getCurrentSession().createQuery("select r from Rol r, Cliente c, Grupo g  where r.name like '"+nombre+"%"+"' and c.id='"+cliente+"' and g.id='"+grupo+"' and r.activo=1 ").list();
		
		return rol;
	}

	@Override
	@Transactional
	public List<Rol> getByname(String nombre) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Rol> rol =getCurrentSession().createQuery("select r from Rol r, Cliente c, Grupo g  where r.activo=1 and r.name like '"+nombre+"%").list();
		
		return rol;
	}
	

	@Override
	@Transactional
	public List<Rol> getAllbyIdcliente(long idcliente) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Rol> rol =getCurrentSession().createQuery("select r from  Cliente c join c.roles rol join  rol.rol r where c.id="+idcliente+" and rol.cliente="+idcliente+"").list();
		
		return rol;
	}

	@Override
	@Transactional
	public List<Rol> getById(Long rolId, Long idgrupo) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Rol> rol =getCurrentSession().createQuery("select r from  Grupo g join Cliente c join c.rolcliente rc join rc.rol r where r.id="+rolId+" and g.id="+idgrupo+"").list();
		return rol;
	}
	
	
	
	@Override
	@Transactional
	public void agregarTxRol(Long Idrol, List<TransaccionDto> Transac) {
		// TODO Auto-generated method stub
		Rol rol=(Rol) getCurrentSession().load(Rol.class,Idrol );
		for(TransaccionDto c : Transac){
	          Transaccion tx = (Transaccion)getCurrentSession().get(Transaccion.class, c.getId());
	          rol.getTransac().add(tx); 
	  }
		getCurrentSession().saveOrUpdate(rol);
		}
		
	
	
	@Override
	@Transactional
	public void agregarTrasaccionRol(Long idRol, List<TransaccionDto> transac) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<RolCliente> tr= getCurrentSession().createQuery("from RolCliente where id_rol="+idRol+"").list();
		System.out.println("rolcliente es "+tr.isEmpty());
		if(tr!=null&&!tr.isEmpty())
		{RolCliente  rc = tr.get(0);
		rc.getTransaccion().clear();
		for(TransaccionDto c : transac){
	          Transaccion tx = (Transaccion)getCurrentSession().get(Transaccion.class, c.getId());
	          rc.getTransaccion().add(tx);
	          getCurrentSession().saveOrUpdate(rc);
	  }
		}
		
	}

	


}




