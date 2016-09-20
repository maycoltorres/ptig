package com.gamasoft.hps.sab.repository.hibernate;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gamasoft.hps.sab.domain.Cliente;
import com.gamasoft.hps.sab.domain.Grupo;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.GrupoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.GrupoRepository;
/**
*
* @author wospino
*/
@Repository
public class HibernateGrupoRepository extends HibernateRepository implements GrupoRepository{
	
	@Autowired
	public HibernateGrupoRepository(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	@Transactional
	public void add(Grupo t) {
		Validate.notNull(t, "el  Grupo no puede estar nulo");
		t.setActivo(true);
		getCurrentSession().saveOrUpdate(t);
	}

	@Transactional
	public void update(Grupo t) throws RepositoryException {
		getCurrentSession().saveOrUpdate(t);
	}

	@Transactional
	public void remove(Long id) throws RepositoryException {
		// TODO Auto-generated method stub
		 Grupo grupo = (Grupo) getCurrentSession().get(Grupo.class, id);
		 grupo.setActivo(false);
	        if(grupo!=null){
	        	getCurrentSession().saveOrUpdate(grupo);
	        }
	}

	@Transactional
	public Grupo getById(long id) {
		// TODO Auto-generated method stub
		Grupo grupo = (Grupo) getCurrentSession().load(Grupo.class, id);
		try{
		System.out.println("El numero de clientes en este grupo es "+grupo.getClientes().size());
		return grupo;}
		catch(Exception e){
			return grupo;
		}
       	}


	@Transactional
	public List<Grupo> getAll() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Grupo> grupos = getCurrentSession().createQuery("from Grupo where activo=true").list();
		return grupos;
	}

	@Transactional
	public List<Grupo> getAllInactivos() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Grupo> grupos = getCurrentSession().createQuery("from Grupo where activo=false").list();
		return grupos;
	}
		

	@Override
	@Transactional
	 public void agregarClienteAGrupo(List<ClienteDto> clienteDto, Long idGrupo) {
		  Grupo grupo = (Grupo) getCurrentSession().get(Grupo.class, idGrupo);
		  grupo.getClientes().clear();
		  for(ClienteDto c : clienteDto){
		          Cliente cliente = (Cliente)getCurrentSession().get(Cliente.class, c.getId());
		          grupo.getClientes().add(cliente);
		          getCurrentSession().saveOrUpdate(grupo);
		  }
		}
	
	@Override
	@Transactional
	public void eliminarClientesDeGrupo(List<ClienteDto> clienteDto,long grupoId) {
		 Grupo grupo = (Grupo) getCurrentSession().get(Grupo.class, grupoId);
		 grupo.getClientes().clear();
		  for(ClienteDto c : clienteDto){
		          Cliente cliente = (Cliente)getCurrentSession().get(Cliente.class, c.getId());
		          grupo.getClientes().add(cliente);
		          getCurrentSession().saveOrUpdate(grupo);
		  }
	}
	
	
	@Override
	@Transactional
	public String agregarClienteAGrupo(long grupoId, long clienteId) {
		 Grupo grupo = (Grupo)getCurrentSession().get(Grupo.class, grupoId);
		 Cliente c=(Cliente) getCurrentSession().get(Cliente.class, clienteId);
		 if(c!=null){
			 if(grupo!=null){
		 grupo.getClientes().add(c);
		 this.getCurrentSession().saveOrUpdate(grupo);
		 }else {
			return "el grupo no existe";
		 }}else{
			 return "el cliente no existe";
		 }
		return null;
		 
	}
	
	
	@Override
	@Transactional
	public void quitarClienteAGrupo(long grupoId, long clienteId) {
		 Grupo grupo = (Grupo)getCurrentSession().get(Grupo.class, grupoId);
		
		 Cliente c=(Cliente) getCurrentSession().get(Cliente.class, clienteId);
		 if(c!=null){
			 if(grupo!=null){
		 grupo.getClientes().remove(c);
		 this.getCurrentSession().saveOrUpdate(grupo);
		 }else {
			 System.out.println("el grupo no existe");
		 }}else{
			 System.out.println("el cliente no existe");
		 }
		 
	}
	
	
	

	@Override
	public GrupoDto getGrupoCliente(long grupoId) {
		return null;
		// TODO Auto-generated method stub
		
	}


	@Override
	@Transactional
	public void remove(long id) throws RepositoryException {
		Grupo grupo = (Grupo)getCurrentSession().get(Grupo.class, id);
		grupo.setActivo(false);
        if(grupo!=null){
        	getCurrentSession().update(grupo);
        }
	}
	
	@Override
	public List<Grupo> getPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Transactional
	public List<Grupo> getByname(String nombre) {
		@SuppressWarnings("unchecked")
		List<Grupo> grupo =getCurrentSession().createQuery("from Grupo where nombre like '"+nombre+"%"+"'"+" and Activo=1").list();

		return grupo;
	}
	@Override
	@Transactional
	public List<Grupo> getByname(String nombre, Long cliente, Long idgrupo) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Grupo> grupo =getCurrentSession().createQuery("from Grupo g join fetch g.clientes cliente where g.nombre like '"+nombre+"%"+"' and cliente.id='"+cliente+"' and g.id='"+idgrupo+"' and g.activo=1").list();

		return grupo;
	}
	@Override
	@Transactional
	public List<Grupo> getGrupoIdCliente(long idCliente) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Grupo> g = getCurrentSession().createQuery("select g from Grupo g join g.clientes c where c.id="+idCliente+"").list();
		return g;
	}
	
}
