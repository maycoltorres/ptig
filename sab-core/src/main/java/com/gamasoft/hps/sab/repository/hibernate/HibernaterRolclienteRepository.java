package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Cliente;
import com.gamasoft.hps.sab.domain.Rol;
import com.gamasoft.hps.sab.domain.RolCliente;
import com.gamasoft.hps.sab.domain.Transaccion;
import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.dto.RolClienteDto;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.RolclienteRepository;
@Repository
public class HibernaterRolclienteRepository  extends HibernateRepository implements RolclienteRepository {

	
	 @Autowired
	    public HibernaterRolclienteRepository(SessionFactory sessionFactory) {
	        setSessionFactory(sessionFactory);
	    }
	
	
	@Override
	@Transactional
	public Long agregarRolCliente0(Long clienteID, Long rolId) {
		// TODO Auto-generated method stub
		Rol rol =(Rol) getCurrentSession().load(Rol.class, rolId);
		Cliente cliente =(Cliente) getCurrentSession().get(Cliente.class,clienteID );
		RolCliente rolcliente = new RolCliente();
		rolcliente.setRol(rol);
		rolcliente.setCliente(cliente);
		getCurrentSession().save(rolcliente);
		return rolcliente.getId();
	   	  }
	 
	@Override
	@Transactional
	public Long agregarRolCliente(Long clienteID, Long rolId) {
		// TODO Auto-generated method stub
		Rol rol =(Rol) getCurrentSession().load(Rol.class, rolId);
		System.out.println("transacciones del rol "+rol.getTransac().isEmpty());
		Cliente cliente =(Cliente) getCurrentSession().get(Cliente.class,clienteID );
		RolCliente rolcliente = new RolCliente();
		rolcliente.setRol(rol);
		rolcliente.setNombre(rol.getName());
		rolcliente.setActivo(true);
		rolcliente.setCliente(cliente);
		rolcliente.getTransaccion().addAll(rol.getTransac());
		getCurrentSession().save(rolcliente);
		return rolcliente.getId();
	   	  }

	@Transactional
	public void removerRolCliente(Long clienteID, Long rolId) {
	
		
	}
	
	
	@Override
	@Transactional
	public String elegirRolUser(Long idUser, List<RolClienteDto> rol) {
		for(RolClienteDto rcdto: rol){
			RolCliente r = (RolCliente)getCurrentSession().get(RolCliente.class, rcdto.getId());
			agregarUserRolCliente(idUser,r.getId());
			
		}
		return "Roles Adicionados";
	}
	

	@Override
	@Transactional
	public String  elegirRolesUser(Long idCliente, List<RolDto> rol) {
		// TODO Auto-generated method stub
		for(RolDto rdto:rol){
			Rol r = (Rol) getCurrentSession().load(Rol.class, rdto.getId());
			agregarRolCliente(idCliente,r.getId());
		}
		return "Roles Agregados";
		
	}

	
	
	@Override
	@Transactional
	public void agregarTxcliente(Long transac, Long idrolcliente) {
		// TODO Auto-generated method stub
		Transaccion tx =(Transaccion) getCurrentSession().load(Transaccion.class, transac);
		RolCliente rolcliente =(RolCliente) getCurrentSession().load(RolCliente.class,idrolcliente );
		if(rolcliente.getTransaccion()!=null){
		rolcliente.getTransaccion().add(tx);
		tx.getRolcliente().add(rolcliente);
		}
		
	}

	@Override
	@Transactional
	public void agregarUserRolCliente(Long id_user, Long id_rolcliente) {
		// TODO Auto-generated method stub
		User user=(User) getCurrentSession().load(User.class,id_user );
		RolCliente rolcliente =(RolCliente) getCurrentSession().load(RolCliente.class,id_rolcliente );
		if(rolcliente.getUser()!=null){
		user.getRolcliente().add(rolcliente);
		rolcliente.getUser().add(user);
	
		}
	}

	@Override
	public void add(Rol r) {
		// TODO Auto-generated method stub
		
	}


	

	@Override
	@Transactional
	public List<Transaccion> getTransacciones() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Transaccion> t = getCurrentSession().createQuery("from Transaccion where obligatorio=0").list();
		return t;
	}

	

	@Override
	@Transactional
	public List<Transaccion> getTransaccionesIdRol(Long idrol) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Transaccion> t = getCurrentSession().createQuery("select t from Rol r join r.transac t where r.id="+idrol+"").list();
		return t;
	}

	@Override
	@Transactional
	public void adicionaRolesCliente(Long idCliente, List<RolDto> roldto) {
		// TODO Auto-generated method stub
		Cliente cliente=(Cliente) getCurrentSession().load(Cliente.class,idCliente );
		RolCliente rc = new RolCliente();
		
		for(RolDto rcdto: roldto){
			Rol r = (Rol)getCurrentSession().get(Rol.class, rcdto.getId());
			rc.setRol(r);
			rc.setCliente(cliente);
			getCurrentSession().saveOrUpdate(rc);
		}
		
		
	}

	@Override
	@Transactional
	public List<RolCliente> getAllactivos(Long idCliente, Long idgrupo) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<RolCliente> rc= getCurrentSession().createQuery("from RolCliente where activo=1 and id_cliente="+idCliente+"").list();
		return rc;
	}

	@Override
	@Transactional
	public void modificarrol(RolCliente rc) {
	 getCurrentSession().saveOrUpdate(rc);
	 	
	}

	@Override
	@Transactional
	public void add(RolCliente t) {
		getCurrentSession().saveOrUpdate(t);		
	}

	@Override
	@Transactional
	public void update(RolCliente t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void remove(long id) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public RolCliente getById(long id) {
		// TODO Auto-generated method stub
		try{
		RolCliente rc = (RolCliente) getCurrentSession().load(RolCliente.class, id);
		System.out.println("el id es "+rc.getId());
		return rc;}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public List<RolCliente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional
	public List<RolCliente> getAllinactivos(Long idCliente) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<RolCliente> rc= getCurrentSession().createQuery("from RolCliente where activo=0 and id_cliente="+idCliente+"").list();
		return rc;
		
	}


	@Override
	@Transactional
	public void adicionarolesusuario(Long iduser, List<RolClienteDto> rol) {
		// TODO Auto-generated method stub
		User u =(User)getCurrentSession().load(User.class, iduser);
		for(RolClienteDto c : rol){
	          RolCliente rc = (RolCliente)getCurrentSession().get(RolCliente.class, c.getId());
	          u.getRolcliente().add(rc);	           
	  }
		getCurrentSession().saveOrUpdate(u);
		
	}


	@Override
	@Transactional
	public void agregarTrasaccionRolUser(Long idRol,List<TransaccionDto> transac) {
		RolCliente rol=(RolCliente) getCurrentSession().load(RolCliente.class,idRol );
		rol.getTransaccion().clear();
		for(TransaccionDto c : transac){
	          Transaccion tx = (Transaccion)getCurrentSession().get(Transaccion.class, c.getId());
	          rol.getTransaccion().add(tx); 
	  }
		getCurrentSession().saveOrUpdate(rol);
		
	}

	@Override
	@Transactional
	public void agregarTrasaccionRol(Long idRol, List<TransaccionDto> transac) {
		// TODO Auto-generated method stub
				Rol rol=(Rol) getCurrentSession().load(Rol.class,idRol );
				for(TransaccionDto c : transac){
			          Transaccion tx = (Transaccion)getCurrentSession().get(Transaccion.class, c.getId());
			          rol.getTransac().add(tx); 
			  }
				getCurrentSession().saveOrUpdate(rol);
	}
	@Override
	@Transactional
	public List<Transaccion> getTransaccionesIdRolCliente(long idrol) {
		// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
				List<Transaccion> t = getCurrentSession().createQuery("select t from RolCliente r join r.transaccion t where r.id="+idrol+"").list();
				return t;
	}


	@Override
	@Transactional
	public List<RolCliente> getRolesByID(long idUser) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<RolCliente> roles = getCurrentSession().createQuery("select rc from RolCliente rc join rc.user u where u.id="+idUser+"  ").list();
		return roles;
	}
	
	}